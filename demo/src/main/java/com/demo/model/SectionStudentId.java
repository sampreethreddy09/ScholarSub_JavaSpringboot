package com.demo.model;

import java.io.Serializable;

public class SectionStudentId implements Serializable {
    private String section;
    private String student;

    // Constructors
    public SectionStudentId() {
    }

    public SectionStudentId(String section, String student) {
        this.section = section;
        this.student = student;
    }

    // getters, setters

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

}