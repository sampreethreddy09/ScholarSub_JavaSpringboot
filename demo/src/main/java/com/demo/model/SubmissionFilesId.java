package com.demo.model;

import java.io.Serializable;


public class SubmissionFilesId implements Serializable {
    
    private int submission;
    private int file;

    // Constructors

    public SubmissionFilesId() {
    }

    public SubmissionFilesId(int submission, int file) {
        this.submission = submission;
        this.file = file;
    }

    // getters and setters

    public int getSubmission() {
        return submission;
    }

    public void setSubmission(int submission) {
        this.submission = submission;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    
}
