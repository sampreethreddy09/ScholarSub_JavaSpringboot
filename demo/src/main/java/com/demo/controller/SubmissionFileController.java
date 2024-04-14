package com.demo.controller;

import com.demo.dto.FileDTO;
import com.demo.service.SubmissionFileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SubmissionFileController {

    private final SubmissionFileService submissionFileService;

    public SubmissionFileController(SubmissionFileService submissionFileService) {
        this.submissionFileService = submissionFileService;
    }

    @GetMapping("/submission/file/{submissionId}")
    public List<FileDTO> getFilesBySubmissionId(@PathVariable int submissionId) {
        return submissionFileService.getFilesBySubmissionId(submissionId);
    }
}
