package com.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.dto.AssignmentDTO;
import com.demo.dto.AssignmentDetailsDTO;
import com.demo.dto.FileDTO;
import com.demo.model.Assignment;
import com.demo.model.AssignmentFiles;
import com.demo.model.Fil;
import com.demo.repository.AssignmentFilesRepository;
import com.demo.repository.AssignmentRepository;
import com.demo.repository.FileRepository;
import com.demo.repository.SectionRepository;
import com.demo.repository.SectionStudentRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.io.IOException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final SectionStudentRepository sectionStudentRepository;
    private final SectionRepository sectionRepository;
    private final AssignmentFilesRepository assignmentFilesRepository;
    private final FileRepository fileRepository;

    private final FileService fileService;
    
    public AssignmentService(AssignmentRepository assignmentRepository, SectionStudentRepository sectionStudentRepository, AssignmentFilesRepository assignmentFilesRepository,
    FileRepository fileRepository, SectionRepository sectionRepository,FileService fileService) {
        this.assignmentRepository = assignmentRepository;
        this.sectionStudentRepository = sectionStudentRepository;
        this.assignmentFilesRepository = assignmentFilesRepository;
        this.fileRepository = fileRepository;
        this.sectionRepository = sectionRepository;
        this.fileService = fileService;
    }

    public List<Assignment> loadLiveAssignmentsByTeacher(String teacherId) {
        List<String> sectionIds = sectionRepository.findSectionIdsByTeacherId(teacherId);
        Date currentTime = new Date();
        return assignmentRepository.findBySectionIdIn(sectionIds)
                .stream()
                .filter(assignment -> assignment.getEndTime().after(currentTime))
                .collect(Collectors.toList());
    }

    public List<Assignment> loadPastAssignmentsByTeacher(String teacherId) {
        List<String> sectionIds = sectionRepository.findSectionIdsByTeacherId(teacherId);
        Date currentTime = new Date();
        return assignmentRepository.findBySectionIdIn(sectionIds)
                .stream()
                .filter(assignment -> assignment.getEndTime().before(currentTime))
                .collect(Collectors.toList());
    }

    public List<Assignment> loadLiveAssignments(String studentId) {
        List<String> sectionIds = sectionStudentRepository.findSectionIdsByStudentId(studentId);
        Date currentTime = new Date();
        return assignmentRepository.findBySectionIdIn(sectionIds)
                .stream()
                .filter(assignment -> assignment.getEndTime().after(currentTime))
                .collect(Collectors.toList());
    }

    public List<Assignment> loadPastAssignments(String studentId) {
        List<String> sectionIds = sectionStudentRepository.findSectionIdsByStudentId(studentId);
        Date currentTime = new Date();
        return assignmentRepository.findBySectionIdIn(sectionIds)
                .stream()
                .filter(assignment -> assignment.getEndTime().before(currentTime))
                .collect(Collectors.toList());
    }

    public AssignmentDetailsDTO loadAssignmentDetails(int assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found"));

        // Load assignment files associated with the assignmentId
        List<AssignmentFiles> assignmentFilesList = assignmentFilesRepository.findByAssignmentId(assignmentId);

        // Collect file IDs associated with the assignment
        List<Integer> fileIds = assignmentFilesList.stream()
                .map(AssignmentFiles::getFile)
                .map(Fil::getId)
                .collect(Collectors.toList());

        // Load file details based on the file IDs
        List<Fil> files = fileRepository.findAllById(fileIds);

        // Prepare AssignmentDTO
        AssignmentDTO assignmentDTO = new AssignmentDTO();
        assignmentDTO.setId(assignment.getId());
        assignmentDTO.setName(assignment.getName());
        assignmentDTO.setStartTime(assignment.getStartTime());
        assignmentDTO.setEndTime(assignment.getEndTime());
        assignmentDTO.setAllowLateSubmission(assignment.isAllowLateSubmission());
        assignmentDTO.setScheduleRightNow(assignment.isScheduleRightNow());
        assignmentDTO.setSecId(assignment.getSection().getId()); // Set only sec_id
        assignmentDTO.setConstraints(assignment.getConstraints());
        assignmentDTO.setDescription(assignment.getDescription());
        assignmentDTO.setMaxMarks(assignment.getMaxMarks());
        assignmentDTO.setInputFilesThere(assignment.isInputFilesThere());
        assignmentDTO.setEvaluated(assignment.isEvaluated());

        // Prepare FileDTOs
        List<FileDTO> fileDTOs = files.stream()
                .map(file -> {
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setName(file.getName());
                    fileDTO.setPath(file.getPath());
                    fileDTO.setType(file.getType());
                    return fileDTO;
                })
                .collect(Collectors.toList());

        // Prepare AssignmentDetailsDTO
        AssignmentDetailsDTO assignmentDetailsDTO = new AssignmentDetailsDTO();
        assignmentDetailsDTO.setAssignment(assignmentDTO);
        assignmentDetailsDTO.setFiles(fileDTOs);

        return assignmentDetailsDTO;
    }


    @Transactional
    public ResponseEntity<String> uploadAssignmentWithFile( MultipartFile[] files,
                                            String aname,
                                            Date endTime,
                                            Date startTime,
                                            boolean allowLateSubmission,
                                            int maxMarks,
                                            String description,
                                            String constraints,
                                            String selectedOption,
                                            boolean isRytNow,
                                            boolean isInput) {

    try {
        MultipartFile file;
        file = files[0];
        
        if(isInput){
            if (files == null || files.length == 0 || files[0] == null || files[0].isEmpty()) {
                return ResponseEntity.badRequest().body("No file uploaded.");
            }                                     
            file = files[0];
            fileService.validateFileExtension(file);
            fileService.validateFileSize(file);
        }


        // Create assignment record
        Assignment assignment = new Assignment();
        assignment.setName(aname);

        // Determine the start time based on the provided logic
        if (isRytNow) {
            assignment.setStartTime(new Date()); // Set start time to current time
        } else {
            // Assuming the format of endTime is "yyyy-MM-dd'T'HH:mm:ss"
            Date specifiedStartTime = startTime;
            assignment.setStartTime(specifiedStartTime);
        }

        assignment.setEndTime(endTime); // Assuming end time is in a parseable format
        assignment.setAllowLateSubmission(allowLateSubmission);
        assignment.setScheduleRightNow(isRytNow);
        assignment.setSection(sectionRepository.findById(selectedOption).orElseThrow(() -> new EntityNotFoundException("Section not found")));
        assignment.setDescription(description);
        assignment.setConstraints(constraints);
        assignment.setInputFilesThere(isInput);
        assignment.setMaxMarks(maxMarks);

        // Save assignment record
        Assignment savedAssignment = assignmentRepository.save(assignment);

        int a_id = savedAssignment.getId();

        if(isInput)
        {
            //create file record
            String fileName = file.getOriginalFilename();
            String filePath = fileService.storeFile(file, a_id);
            int fileSize = (int) file.getSize(); // Cast the long value to an int
            int fileId = fileService.createFileRecord(fileName, filePath, fileSize, file.getContentType());

            attachFileToAssignment(a_id, fileId);
        }
        
        return ResponseEntity.ok("Assignment created successfully.");
    } catch (IOException e) {
        e.printStackTrace(); // Handle or log the exception appropriately
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file.");
    }
}

    public void attachFileToAssignment(int assignmentId, int fileId) {
        // Fetch the submission and file objects by their IDs
        Optional<Assignment> assignmentOptional = assignmentRepository.findById(assignmentId);
        Optional<Fil> fileOptional = fileRepository.findById(fileId);

        if (assignmentOptional.isPresent() && fileOptional.isPresent()) {
            // Create a new SubmissionFiles object
            AssignmentFiles assignmentFiles = new AssignmentFiles();
            assignmentFiles.setAssignment(assignmentOptional.get());
            assignmentFiles.setFile(fileOptional.get());

            // Save the submission-file relationship in the database
            assignmentFilesRepository.save(assignmentFiles);
        } else {
            // Handle case where submission or file with the given IDs doesn't exist
            throw new IllegalArgumentException("Assignment or File not found");
        }
    }

    // public String deleteAssignment(int assignmentId) {

    //     try {
    //         // Retrieve file paths associated with the assignment
    //         List<String> filePaths = fileRepository.findPathsByAssignmentId(assignmentId);

    //         // Delete files from the filesystem
    //         for (String path : filePaths) {
    //             Fil file = new Fil(path);
    //             if (file.exists()) {
    //                 file.delete();
    //             }
    //         }

    //         // Delete assignment from the database
    //         assignmentRepository.deleteById(assignmentId);

    //         // Delete files from the database
    //         fileRepository.deleteByAssignmentId(assignmentId);

    //         return "Assignment deleted successfully";
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return "Error in deleting assignment";
    //     }
    // }

    
}

