package com.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Submission_Files")
@IdClass(SubmissionFilesId.class)
public class SubmissionFiles {

    @Id
    @ManyToOne
    @JoinColumn(name = "sub_id")
    private Submission submission;

    @Id
    @ManyToOne
    @JoinColumn(name = "f_id")
    private Fil file;

    // Constructors

    public SubmissionFiles() {
    }

    public SubmissionFiles(Submission submission, Fil file) {
        this.submission = submission;
        this.file = file;
    }

    // getters and setters

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public Fil getFile() {
        return file;
    }

    public void setFile(Fil file) {
        this.file = file;
    }
}
