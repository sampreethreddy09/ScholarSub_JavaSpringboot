package com.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_id")
    private int subId;

    @Column(name = "obtained_marks")
    private Integer obtainedMarks;

    @Column(name = "feedback", length = 200)
    private String feedback;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_id", referencedColumnName = "sub_id")
    private Submission submission;

    // Constructors, getters, and setters

    public Result() {
    }

    public Result(Integer obtainedMarks, String feedback) {
        this.obtainedMarks = obtainedMarks;
        this.feedback = feedback;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public Integer getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(Integer obtainedMarks) {
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
