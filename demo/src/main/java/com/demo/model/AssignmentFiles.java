package com.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Assignment_Files")
@IdClass(AssignmentFilesId.class)
public class AssignmentFiles {

    @Id
    @ManyToOne
    @JoinColumn(name = "a_id")
    private Assignment assignment;

    @Id
    @ManyToOne
    @JoinColumn(name = "f_id")
    private Fil file;

    // Constructors

    public AssignmentFiles() {
    }

    public AssignmentFiles(Assignment assignment, Fil file) {
        this.assignment = assignment;
        this.file = file;
    }

    // getters and setters

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Fil getFile() {
        return file;
    }

    public void setFile(Fil file) {
        this.file = file;
    }
}

