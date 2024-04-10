package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.SubmissionDTO;
import com.demo.model.Assignment;
import com.demo.model.Fil;
import com.demo.model.Student;
import com.demo.model.Submission;
import com.demo.model.SubmissionFiles;
import com.demo.repository.AssignmentRepository;
import com.demo.repository.FileRepository;
import com.demo.repository.StudentRepository;
import com.demo.repository.SubmissionFilesRepository;
import com.demo.repository.SubmissionRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;
    private StudentRepository studentRepository;
    private FileRepository fileRepository;
    private AssignmentRepository assignmentRepository;
    private SubmissionFilesRepository submissionFilesRepository;

    public SubmissionService(SubmissionRepository submissionRepository, StudentRepository studentRepository,
            FileRepository fileRepository, AssignmentRepository assignmentRepository,
            SubmissionFilesRepository submissionFilesRepository) {
        this.submissionRepository = submissionRepository;
        this.studentRepository = studentRepository;
        this.fileRepository = fileRepository;
        this.assignmentRepository = assignmentRepository;
        this.submissionFilesRepository = submissionFilesRepository;
    }

    public int createSubmission(String sId, String aId, Date tos) {
        // Fetch student and assignment objects by their IDs

        Optional<Student> studentOptional = studentRepository.findById(sId);

        // Parse aId as an int
        int assignmentId = Integer.parseInt(aId);

        Optional<Assignment> assignmentOptional = assignmentRepository.findById(assignmentId);

        if (studentOptional.isPresent() && assignmentOptional.isPresent()) {
            // Create a new Submission object
            Submission submission = new Submission();
            submission.setTos(tos);
            submission.setAssignment(assignmentOptional.get());
            submission.setStudent(studentOptional.get());

            // Save the submission object in the database
            Submission savedSubmission = submissionRepository.save(submission);
        
            return savedSubmission.getId();
        } else {
            // Handle case where student or assignment with the given IDs doesn't exist
            throw new IllegalArgumentException("Student or Assignment not found");
        }
    }

    public void attachFileToSubmission(int submissionId, int fileId) {
            // Fetch the submission and file objects by their IDs
            Optional<Submission> submissionOptional = submissionRepository.findById(submissionId);
            Optional<Fil> fileOptional = fileRepository.findById(fileId);

            if (submissionOptional.isPresent() && fileOptional.isPresent()) {
                // Create a new SubmissionFiles object
                SubmissionFiles submissionFiles = new SubmissionFiles();
                submissionFiles.setSubmission(submissionOptional.get());
                submissionFiles.setFile(fileOptional.get());

                // Save the submission-file relationship in the database
                submissionFilesRepository.save(submissionFiles);
            } else {
                // Handle case where submission or file with the given IDs doesn't exist
                throw new IllegalArgumentException("Submission or File not found");
            }
        }

    public List<SubmissionDTO> getSubmissionsByStudentId(String studentId) {
        List<Submission> submissions = submissionRepository.findByStudentId(studentId);
        List<SubmissionDTO> submissionDTOs = new ArrayList<>();

        for (Submission submission : submissions) {
            SubmissionDTO dto = new SubmissionDTO();
            dto.setId(submission.getId());
            dto.setTos(submission.getTos());
            dto.setAssignmentId(submission.getAssignment().getId()); // Assuming you want to include assignment ID
            submissionDTOs.add(dto);
        }

        return submissionDTOs;
    }

    // public SubmissionStatsDto fetchSubmissionStats(int assignmentId) {
    //     List<Object[]> submissionsWithMarksAndFeedback = submissionRepository.fetchSubmissionsWithMarksAndFeedback(assignmentId);
    //     Integer submissionCount = submissionRepository.countSubmissionsByAssignmentId(assignmentId);

    //     return new SubmissionStatsDto(submissionCount, submissionsWithMarksAndFeedback);
    // }
}