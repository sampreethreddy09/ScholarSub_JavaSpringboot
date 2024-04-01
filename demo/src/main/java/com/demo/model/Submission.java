package com.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Submission")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_id")
    private int id;

    @Column(name = "tos", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "a_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "s_id")
    private Student student;

    // constructors

    public Submission() {
    }

    public Submission(int id, LocalDateTime timestamp, Assignment assignment, Student student) {
        this.id = id;
        this.timestamp = timestamp;
        this.assignment = assignment;
        this.student = student;
    }

    // getters and setters
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
