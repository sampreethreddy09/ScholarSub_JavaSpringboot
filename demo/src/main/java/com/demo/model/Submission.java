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
    private LocalDateTime tos;

    @ManyToOne
    @JoinColumn(name = "a_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "s_id")
    private Student student;

    // constructors

    public Submission() {
    }

    public Submission(int id, LocalDateTime tos, Assignment assignment, Student student) {
        this.id = id;
        this.tos = tos;
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

    public LocalDateTime getTos() {
        return tos;
    }

    public void setTos(LocalDateTime tos) {
        this.tos = tos;
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
