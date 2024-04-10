package com.demo.dto;

import java.util.Date;

public class SubmissionDTO {

    private int id;
    private Date tos;
    private int assignmentId;

    // Constructors, getters, and setters

    public SubmissionDTO() {
    }

    public SubmissionDTO(int id, Date tos, int assignmentId) {
        this.id = id;
        this.tos = tos;
        this.assignmentId = assignmentId;
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
}

