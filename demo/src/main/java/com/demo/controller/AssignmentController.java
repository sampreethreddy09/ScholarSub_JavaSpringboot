package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.AssignmentDTO;
import com.demo.model.Assignment;
import com.demo.service.AssignmentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/assignments/live/{studentId}")
     public List<AssignmentDTO> loadLiveAssignments(@PathVariable String studentId) {
        List<Assignment> assignments = assignmentService.loadLiveAssignments(studentId);
        List<AssignmentDTO> assignmentDTOs = new ArrayList<>();

        for (Assignment assignment : assignments) {
            AssignmentDTO dto = new AssignmentDTO();
            dto.setId(assignment.getId());
            dto.setName(assignment.getName());
            dto.setStartTime(assignment.getStartTime());
            dto.setEndTime(assignment.getEndTime());
            dto.setAllowLateSubmission(assignment.isAllowLateSubmission());
            dto.setScheduleRightNow(assignment.isScheduleRightNow());
            dto.setSecId(assignment.getSection().getId()); // Set only sec_id
            assignmentDTOs.add(dto);
        }

        return assignmentDTOs;
    }

    @GetMapping("/assignments/past/{studentId}")
    public List<Assignment> loadPastAssignments(@PathVariable String studentId) {
        return assignmentService.loadPastAssignments(studentId);
    }

    @GetMapping("/assignment/{assignmentId}")
    public Assignment loadAssignmentDetails(@PathVariable int assignmentId) {
        return assignmentService.loadAssignmentDetails(assignmentId);
    }
}
