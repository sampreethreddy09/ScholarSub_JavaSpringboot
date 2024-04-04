package com.demo.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

public class CreateAssignmentDTO {
    private String name;
    private LocalDateTime endTime;
    private boolean isRytNow;
    private String selectedOption;
    private boolean isInput;
    private String description;
    private String constraints;
    private boolean allowLateSubmission;
    private int maxMarks;
    private MultipartFile file; // This field will hold the uploaded file

    private LocalDateTime startTime;


    public CreateAssignmentDTO(String name, LocalDateTime endTime, boolean isRytNow, String selectedOption, boolean isInput,
            String description, String constraints, boolean allowLateSubmission, int maxMarks, MultipartFile file) {
        this.name = name;
        this.endTime = endTime;
        this.isRytNow = isRytNow;
        this.selectedOption = selectedOption;
        this.isInput = isInput;
        this.description = description;
        this.constraints = constraints;
        this.allowLateSubmission = allowLateSubmission;
        this.maxMarks = maxMarks;
        this.file = file;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public LocalDateTime getEndTime() {
        return endTime;
    }


    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }


    public boolean isRytNow() {
        return isRytNow;
    }


    public void setRytNow(boolean isRytNow) {
        this.isRytNow = isRytNow;
    }


    public String getSelectedOption() {
        return selectedOption;
    }


    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }


    public boolean isInput() {
        return isInput;
    }


    public void setInput(boolean isInput) {
        this.isInput = isInput;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getConstraints() {
        return constraints;
    }


    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }


    public boolean isAllowLateSubmission() {
        return allowLateSubmission;
    }


    public void setAllowLateSubmission(boolean allowLateSubmission) {
        this.allowLateSubmission = allowLateSubmission;
    }


    public int getMaxMarks() {
        return maxMarks;
    }


    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }


    public MultipartFile getFile() {
        return file;
    }


    public void setFile(MultipartFile file) {
        this.file = file;
    }


    public CreateAssignmentDTO() {
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }


    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    // Getters and setters
    

    
}

