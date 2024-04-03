package com.demo.dto;

import java.util.List;

public class AssignmentDetailsDTO {
    private AssignmentDTO assignment;
    // Add other assignment details as needed
    private List<FileDTO> files;

    // constructors

    public AssignmentDetailsDTO() {
    }

    public AssignmentDetailsDTO(AssignmentDTO assignment, List<FileDTO> files) {
        this.assignment = assignment;
        this.files = files;
    }

    // Getters and setters

    public AssignmentDTO getAssignment() {
        return assignment;
    }

    public void setAssignment(AssignmentDTO assignment) {
        this.assignment = assignment;
    }

    public List<FileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<FileDTO> files) {
        this.files = files;
    }
    
}

