package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Assignment;
import com.demo.service.AssignmentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/assignments/live/{studentId}")
    public List<Assignment> loadLiveAssignments(@PathVariable String studentId) {
        return assignmentService.loadLiveAssignments(studentId);
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
