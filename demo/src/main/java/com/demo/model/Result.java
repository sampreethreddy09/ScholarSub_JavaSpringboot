package com.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Result")
public class Result {
    @Id
    @Column(name = "sub_id")
    private int submissionId;

    @Column(name = "total_marks", nullable = false)
    private int totalMarks;

    @Column(name = "obtained_marks")
    private Integer obtainedMarks;

    @OneToOne
    @MapsId
    @JoinColumn(name = "sub_id")
    private Submission submission;


    // constructors

    public Result() {
    }


    public Result(int submissionId, int totalMarks, Integer obtainedMarks, Submission submission) {
        this.submissionId = submissionId;
        this.totalMarks = totalMarks;
        this.obtainedMarks = obtainedMarks;
        this.submission = submission;
    }

    // getters and setters

    public int getSubmissionId() {
        return submissionId;
    }


    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }


    public int getTotalMarks() {
        return totalMarks;
    }


    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }


    public Integer getObtainedMarks() {
        return obtainedMarks;
    }


    public void setObtainedMarks(Integer obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }


    public Submission getSubmission() {
        return submission;
    }


    public void setSubmission(Submission submission) {
        this.submission = submission;
    }
    
   
    
    
}
