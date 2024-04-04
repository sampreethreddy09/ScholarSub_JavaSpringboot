package com.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.model.Fil;
import com.demo.model.SubmissionFiles;
import com.demo.model.SubmissionFilesId;
import com.demo.repository.AssignmentFilesRepository;
import com.demo.repository.SubmissionFilesRepository;
import com.demo.service.FileService;

import jakarta.servlet.http.HttpServletResponse;

public class FilesController {
     private final FileService fileService;
     private final SubmissionFilesRepository submissionFilesRepository;
    

    public FilesController(FileService fileService,SubmissionFilesRepository submissionFilesRepository) {
        this.fileService = fileService;
        this.submissionFilesRepository = submissionFilesRepository;
    }

    // @GetMapping("/fetchSubFiles/{sub_id}/{a_id}")
    // public void fetchSubFiles(@PathVariable String sub_id, @PathVariable String a_id, HttpServletResponse response) throws IOException {
    //     // String fileId = submissionFilesRepository.findById(sub_id, a_id).getFile().getId();

    //     SubmissionFilesId submissionFilesId = new SubmissionFilesId(Integer.parseInt(sub_id),Integer.parseInt(a_id));
    //     Optional<SubmissionFiles> submissionFilesOptional = submissionFilesRepository.findById(submissionFilesId);
    //     if (submissionFilesOptional.isPresent()) {
    //         SubmissionFiles submissionFiles = submissionFilesOptional.get();
    //         int fileId = submissionFiles.getFile().getId();
    //         String filename = submissionFiles.getFile().getName();
        

    //         if (filename != null && filename.endsWith(".pdf")) {
    //             response.setContentType("application/pdf");
    //             File file = new File(filename);
    //             response.setHeader("Content-Disposition", "attachment; filename=" + filename);
    //             try (InputStream inputStream = new FileInputStream(file)) { 
    //                 org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());
    //                 response.flushBuffer();
    //             }
    //         }} else {
    //             response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File not found or not in PDF format.");
    //         }
    // }
}
