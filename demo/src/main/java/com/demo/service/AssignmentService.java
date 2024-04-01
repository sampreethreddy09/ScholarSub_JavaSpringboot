package com.demo.service;

import org.springframework.stereotype.Service;

import com.demo.model.Assignment;
import com.demo.repository.AssignmentRepository;
import com.demo.repository.SectionStudentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final SectionStudentRepository sectionStudentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, SectionStudentRepository sectionStudentRepository) {
        this.assignmentRepository = assignmentRepository;
        this.sectionStudentRepository = sectionStudentRepository;
    }

    public List<Assignment> loadLiveAssignments(String studentId) {
        List<String> sectionIds = sectionStudentRepository.findSectionIdsByStudentId(studentId);
        System.out.println("sectionIds: " + sectionIds);
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("currentTime: " + currentTime);
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

    public Assignment loadAssignmentDetails(int assignmentId) {
        return assignmentRepository.findById(assignmentId).orElse(null);
    }
}
