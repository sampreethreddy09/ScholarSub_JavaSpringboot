package com.demo.controller;

import com.demo.dto.FileDTO;
import com.demo.service.AssignmentFileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssignmentFileController {

    private final AssignmentFileService assignmentFileService;

    public AssignmentFileController(AssignmentFileService assignmentFileService) {
        this.assignmentFileService = assignmentFileService;
    }

    @GetMapping("/assignment/files/{assignmentId}")
    public List<FileDTO> getFilesByAssignmentId(@PathVariable int assignmentId) {
        return assignmentFileService.getFilesByAssignmentId(assignmentId);
    }
}
