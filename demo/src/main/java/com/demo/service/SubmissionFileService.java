package com.demo.service;

import com.demo.dto.FileDTO;
import com.demo.model.SubmissionFiles;
import com.demo.repository.SubmissionFilesRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmissionFileService {

    private final SubmissionFilesRepository submissionFilesRepository;

    public SubmissionFileService(SubmissionFilesRepository submissionFilesRepository) {
        this.submissionFilesRepository = submissionFilesRepository;
    }

    public List<FileDTO> getFilesBySubmissionId(int submissionId) {
        List<SubmissionFiles> submissionFilesList = submissionFilesRepository.findBySubmissionId(submissionId);
        return submissionFilesList.stream()
                .map(this::mapToFileDTO)
                .collect(Collectors.toList());
    }

    private FileDTO mapToFileDTO(SubmissionFiles submissionFiles) {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setName(submissionFiles.getFile().getName());
        fileDTO.setPath(submissionFiles.getFile().getPath());
        fileDTO.setType(submissionFiles.getFile().getType());
        return fileDTO;
    }
}
