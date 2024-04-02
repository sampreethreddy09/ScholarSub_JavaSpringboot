package com.demo.model;
import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "Submission_Files")
public class SubmissionFiles {
    @EmbeddedId
    private SubmissionFilesId id;

    @ManyToOne
    @MapsId("submissionId")
    @JoinColumn(name = "sub_id")
    private Submission submission;

    @ManyToOne
    @MapsId("fileId")
    @JoinColumn(name = "f_id")
    private File file;

    // Constructors

    public SubmissionFiles() {
    }

    public SubmissionFiles(SubmissionFilesId id, Submission submission, File file) {
        this.id = id;
        this.submission = submission;
        this.file = file;
    }

    // getters and setters

    public SubmissionFilesId getId() {
        return id;
    }

    public void setId(SubmissionFilesId id) {
        this.id = id;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

@Embeddable
class SubmissionFilesId implements Serializable {
    @Column(name = "sub_id")
    private int submissionId;

    @Column(name = "f_id")
    private int fileId;

    // Constructors

    public SubmissionFilesId() {
    }

    public SubmissionFilesId(int submissionId, int fileId) {
        this.submissionId = submissionId;
        this.fileId = fileId;
    }

    // getters and setters

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    
}
