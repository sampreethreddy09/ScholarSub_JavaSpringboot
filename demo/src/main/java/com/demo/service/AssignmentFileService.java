package com.demo.service;

import com.demo.dto.FileDTO;
import com.demo.model.AssignmentFiles;
import com.demo.repository.AssignmentFilesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentFileService {

    private final AssignmentFilesRepository assignmentFilesRepository;

    public AssignmentFileService(AssignmentFilesRepository assignmentFilesRepository) {
        this.assignmentFilesRepository = assignmentFilesRepository;
    }

    public List<FileDTO> getFilesByAssignmentId(int assignmentId) {
        List<AssignmentFiles> assignmentFilesList = assignmentFilesRepository.findByAssignmentId(assignmentId);
        return assignmentFilesList.stream()
                .map(this::mapToFileDTO)
                .collect(Collectors.toList());
    }

    private FileDTO mapToFileDTO(AssignmentFiles assignmentFiles) {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setName(assignmentFiles.getFile().getName());
        fileDTO.setPath(assignmentFiles.getFile().getPath());
        fileDTO.setType(assignmentFiles.getFile().getType());
        return fileDTO;
    }
}
