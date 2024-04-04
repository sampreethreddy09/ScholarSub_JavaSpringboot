package com.demo.dto;

import java.util.Date;


public class ResultDTO {

    private int submissionId;
    private Date tos;
    private int assignmentId;
    private String studentId;
    private int obtainedMarks;
    private String feedback;
    private int maxMarks;

    // Constructors

    public ResultDTO() {}

    public ResultDTO(int submissionId, Date tos, int assignmentId, String studentId, int obtainedMarks, String feedback, int maxMarks) {
        this.submissionId = submissionId;
        this.tos = tos;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.obtainedMarks = obtainedMarks;
        this.feedback = feedback;
        this.maxMarks = maxMarks;
    }

    // getters and setters

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public Date getTos() {
        return tos;
    }

    public void setTos(Date tos) {
        this.tos = tos;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public int getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }

}
