// package com.demo.service;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;
// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.Date;

// @Service
// public class FileStorageService {

//     @Value("${file.upload-dir}")
//     private String uploadDir;

//     public String storeFile(MultipartFile file, String a_id) throws IOException {
//         String assignmentDir = uploadDir + File.separator + a_id;
//         File assignmentFolder = new File(assignmentDir);
//         if (!assignmentFolder.exists()) {
//             assignmentFolder.mkdirs();
//         }
        
//         String filePath = assignmentDir + File.separator + file.getOriginalFilename();
//         Path destination = Paths.get(filePath);
//         Files.copy(file.getInputStream(), destination);
//         return filePath;
//     }

//     public long createFileRecord(String fileName, String filePath, long fileSize, String fileType) {
//         return fileSize;
//         // Implement logic to save file record in database and return the file ID
//     }
// }
