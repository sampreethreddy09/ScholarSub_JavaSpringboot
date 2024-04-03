package com.demo.service;

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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final SectionStudentRepository sectionStudentRepository;
    private final SectionRepository sectionRepository;
    private final AssignmentFilesRepository assignmentFilesRepository;
    private final FileRepository fileRepository;

  

    
    public AssignmentService(AssignmentRepository assignmentRepository, SectionStudentRepository sectionStudentRepository, AssignmentFilesRepository assignmentFilesRepository,
    FileRepository fileRepository, SectionRepository sectionRepository) {
        this.assignmentRepository = assignmentRepository;
        this.sectionStudentRepository = sectionStudentRepository;
        this.assignmentFilesRepository = assignmentFilesRepository;
        this.fileRepository = fileRepository;
        this.sectionRepository = sectionRepository;
    }

    public List<Assignment> loadLiveAssignmentsByTeacher(String teacherId) {
        List<String> sectionIds = sectionRepository.findSectionIdsByTeacherId(teacherId);
        LocalDateTime currentTime = LocalDateTime.now();
        return assignmentRepository.findBySectionIdIn(sectionIds)
                .stream()
                .filter(assignment -> assignment.getEndTime().isAfter(currentTime))
                .collect(Collectors.toList());
    }
    public List<Assignment> loadPastAssignmentsByTeacher(String teacherId) {
        List<String> sectionIds = sectionRepository.findSectionIdsByTeacherId(teacherId);
        LocalDateTime currentTime = LocalDateTime.now();
        return assignmentRepository.findBySectionIdIn(sectionIds)
                .stream()
                .filter(assignment -> assignment.getEndTime().isBefore(currentTime))
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

    public List<Assignment> loadLiveAssignments(String studentId) {
        List<String> sectionIds = sectionStudentRepository.findSectionIdsByStudentId(studentId);
        LocalDateTime currentTime = LocalDateTime.now();
        return assignmentRepository.findBySectionIdIn(sectionIds)
                .stream()
                .filter(assignment -> assignment.getEndTime().isAfter(currentTime))
                .collect(Collectors.toList());
    }

    public List<Assignment> loadPastAssignments(String studentId) {
        List<String> sectionIds = sectionStudentRepository.findSectionIdsByStudentId(studentId);
        LocalDateTime currentTime = LocalDateTime.now();
        return assignmentRepository.findBySectionIdIn(sectionIds)
                .stream()
                .filter(assignment -> assignment.getEndTime().isBefore(currentTime))
                .collect(Collectors.toList());
    }

    
}

