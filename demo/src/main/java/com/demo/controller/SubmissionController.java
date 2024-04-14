package com.demo.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.dto.SubmissionDTO;
import com.demo.service.FileService;
import com.demo.service.SubmissionService;

@RestController
@RequestMapping("/api")
public class SubmissionController {

    private FileService fileService;
    private SubmissionService submissionService;

    public SubmissionController(FileService fileService, SubmissionService submissionService) {
        this.fileService = fileService;
        this.submissionService = submissionService;
    }

    @PostMapping("/student/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("files") MultipartFile[] files,
                                             @RequestParam("s_id") String s_id,
                                             @RequestParam("a_id") String a_id,
                                             @RequestParam("end_time") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date end_time,
                                             @RequestParam("allow_late_submission") boolean allow_late_submission) {
        try {
            fileService.submit(files, s_id, a_id, end_time, allow_late_submission);
            return ResponseEntity.status(HttpStatus.OK).body("Files uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload files.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/student/submissions/{studentId}")
    public ResponseEntity<List<SubmissionDTO>> getSubmissionsByStudentId(@PathVariable("studentId") String studentId) {
        List<SubmissionDTO> submissions = submissionService.getSubmissionsByStudentId(studentId);
        if (submissions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @GetMapping("/teacher/submissions/{assignmentId}")
    public ResponseEntity<Map<String, Object>> getSubmissionsByAssignmentId(@PathVariable("assignmentId") String assignmentId) {

        // Convert the String assignmentId to an int
        int assignmentid = Integer.parseInt(assignmentId);
        List<SubmissionDTO> submissions = submissionService.getSubmissionsByAssignmentId(assignmentid);

        int submissionCount = submissions.size();
        
        Map<String, Object> response = new HashMap<>();
        response.put("submissions", submissions);
        response.put("count", submissionCount);

        if (submissions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    // @GetMapping("/submission/fetch/{assignmentId}")
    // public SubmissionStatsDto fetchSubmissionStats(@PathVariable int assignmentId) {
    //     return submissionService.fetchSubmissionStats(assignmentId);
    // }

}
