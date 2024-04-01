package com.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Section_Student")
@IdClass(SectionStudentId.class)
public class SectionStudent {
    @Id
    @ManyToOne
    @JoinColumn(name = "sec_id")
    private Section section;

    @Id
    @ManyToOne
    @JoinColumn(name = "s_id")
    private Student student;

    // constructors

    public SectionStudent() {
    }   
    
    public SectionStudent(Section section, Student student) {
        this.section = section;
        this.student = student;
    }
    
    // Getters and setters

    public Section getSection() {
        return section;
    }


    public void setSection(Section section) {
        this.section = section;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    
    
}
