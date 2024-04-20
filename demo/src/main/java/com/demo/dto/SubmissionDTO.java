package com.demo.dto;

import java.util.Date;

public class SubmissionDTO {

    private int id;
    private Date tos;
    private int assignmentId;
    private String studentId;
    private int ObtainedMarks;
    private String feedback;

    // Constructors, getters, and setters

    public SubmissionDTO() {
    }

    public SubmissionDTO(int id, Date tos, int assignmentId, String studentId, int obtainedMarks, String feedback) {
        this.id = id;
        this.tos = tos;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.ObtainedMarks = obtainedMarks; 
        this.feedback = feedback;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return ObtainedMarks;
    }

    public void setObtainedMarks(int obtainedMarks) {
        ObtainedMarks = obtainedMarks;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}

