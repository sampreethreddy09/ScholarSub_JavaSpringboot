package com.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Result")
public class Result {
    @Id
    @Column(name = "sub_id")
    private int submissionId;

    @Column(name = "obtained_marks", nullable = false)
    private int obtainedMarks;

    @Column(name = "feedback")
    private String feedback;

    @OneToOne
    @MapsId
    @JoinColumn(name = "sub_id")
    private Submission submission;

    public Result(){

    }
    public Result(int submissionId, int obtainedMarks, String feedback, Submission submission) {
        this.submissionId = submissionId;
        this.obtainedMarks = obtainedMarks;
        this.feedback = feedback;
        this.submission = submission;
    }
    public int getSubmissionId() {
        return submissionId;
    }
    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }
    public int getObtainedMarks() {
        return obtainedMarks;
    }
    public void setObtainedMarks(int obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }
    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public Submission getSubmission() {
        return submission;
    }
    public void setSubmission(Submission submission) {
        this.submission = submission;
    }


    

}