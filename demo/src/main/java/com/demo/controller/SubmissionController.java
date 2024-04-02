package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import com.demo.service.FileService;

@RestController
@RequestMapping("/api/student")
public class SubmissionController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
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
}
