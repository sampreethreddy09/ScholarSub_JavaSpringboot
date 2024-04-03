package com.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.demo.model.Fil;
import com.demo.repository.FileRepository;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private FileRepository fileRepository;
    private SubmissionService submissionService;

    public FileService(FileRepository fileRepository, SubmissionService submissionService) {
        this.fileRepository = fileRepository;
        this.submissionService = submissionService;
    }

    public String storeFile(MultipartFile file, int a_id) throws IOException {
        filesPayloadExists(file);
        validateFileSize(file);
        validateFileExtension(file);

        String assignmentDir = uploadDir + File.separator + a_id;
        File assignmentFolder = new File(assignmentDir);
        if (!assignmentFolder.exists()) {
            assignmentFolder.mkdirs();
        }
        
        String filePath = assignmentDir + File.separator + file.getOriginalFilename();
        Path destination = Paths.get(filePath);
        Files.copy(file.getInputStream(), destination);

        return filePath;
    }

    public int createFileRecord(String fileName,String filePath,  int fileSize ,String fileType)  {
            // Create a new File object
            Fil file = new Fil();
            file.setName(fileName);
            file.setType(fileType);
            file.setSize(fileSize);
            file.setPath(filePath);

            // Save the file record in the database
            Fil savedFile = fileRepository.save(file);

            // Return the ID of the saved file record
            return savedFile.getId();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void submit(MultipartFile[] files, String s_id, String a_id, Date end_time, boolean allow_late_submission) throws IOException {
        Date currentTime = new Date();
        
        // Check if deadline has passed and late submission is not allowed
        if (!allow_late_submission && currentTime.after(end_time)) {
            throw new RuntimeException("Deadline has passed, You can't submit now");
        }

        // Create submission
        int submissionId = submissionService.createSubmission(s_id, a_id, currentTime);

        // Store files and create file records
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String filePath = storeFile(file, Integer.parseInt(a_id));
            int fileSize = (int) file.getSize(); // Cast the long value to an int
            int fileId = createFileRecord(fileName, filePath, fileSize, file.getContentType());
            submissionService.attachFileToSubmission(submissionId, fileId);
        }
    }

    // Validate files payload
    public void filesPayloadExists(MultipartFile file) {
        if (file == null) {
            throw new IllegalArgumentException("Missing files");
        }
    }

    // Validate file size
    public void validateFileSize(MultipartFile file) {
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new IllegalArgumentException("File size exceeds the limit of 5 MB.");
        }
    }

    // .pdf','.png', '.jpg', '.jpeg','.sql','.py','.c','.cpp','.js','.ipynb

    // Validate file extension

    public static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".jpg", ".jpeg", ".png", ".pdf",".png",".sql",".py",".c" , ".cpp" ,".java",".js" ,".ipynb");

    public void validateFileExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename != null) {
            boolean isValidExtension = ALLOWED_EXTENSIONS.stream().anyMatch(ext -> filename.toLowerCase().endsWith(ext));
            if (!isValidExtension) {
                throw new IllegalArgumentException("File is not of supported format.");
            }
        }
    }

    
}
