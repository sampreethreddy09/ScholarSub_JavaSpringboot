// package com.demo.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;
// import java.io.File;
// import java.io.IOException;
// import java.util.Date;

// @Service
// public class FileSubmissionService {

//     @Autowired
//     private SubmissionService submissionService;

//     @Autowired
//     private FileStorageService fileStorageService;

//     public void submitFile(MultipartFile[] files, String s_id, String a_id, Date end_time, boolean allow_late_submission) throws IOException {
//         Date currentTime = new Date();
        
//         // Check if deadline has passed and late submission is not allowed
//         if (!allow_late_submission && currentTime.after(end_time)) {
//             throw new RuntimeException("Deadline has passed, You can't submit now");
//         }

//         // Create submission
//         long submissionId = submissionService.createSubmission(s_id, a_id, currentTime);

//         // Store files and create file records
//         for (MultipartFile file : files) {
//             String fileName = file.getOriginalFilename();
//             String filePath = fileStorageService.storeFile(file, a_id);
//             long fileId = fileStorageService.createFileRecord(fileName, filePath, file.getSize(), file.getContentType());
//             submissionService.attachFileToSubmission(submissionId, fileId);
//         }
//     }
// }
