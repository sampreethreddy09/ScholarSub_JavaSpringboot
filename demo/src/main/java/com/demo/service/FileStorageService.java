// package com.demo.service;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import com.demo.model.Fil;
// import com.demo.repository.FileRepository;

// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;

// @Service
// public class FileStorageService {

//     @Value("${file.upload-dir}")
//     private String uploadDir;

//     private FileRepository fileRepository;

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

//     public int createFileRecord(String fileName, String fileType, int fileSize, String filePath) {
//             // Create a new File object
//             Fil file = new Fil();
//             file.setName(fileName);
//             file.setType(fileType);
//             file.setSize(fileSize);
//             file.setPath(filePath);

//             // Save the file record in the database
//             Fil savedFile = fileRepository.save(file);

//             // Return the ID of the saved file record
//             return savedFile.getId();
//     }
// }
