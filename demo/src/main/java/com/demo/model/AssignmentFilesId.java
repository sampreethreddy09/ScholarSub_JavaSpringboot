package com.demo.model;

import java.io.Serializable;

public class AssignmentFilesId implements Serializable {

    private int assignment;
    private int file;

    // Constructors

    public AssignmentFilesId() {
    }

    public AssignmentFilesId(int assignment, int file) {
        this.assignment = assignment;
        this.file = file;
    }

    // getters and setters

    public int getAssignment() {
        return assignment;
    }

    public void setAssignment(int assignment) {
        this.assignment = assignment;
    }

    public int getfile() {
        return file;
    }

    public void setfile(int file) {
        this.file = file;
    }
    
}

