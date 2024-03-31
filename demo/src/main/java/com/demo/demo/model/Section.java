package com.demo.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "Section")
public class Section {
    @Id
    @Column(name = "sec_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "t_id")
    private Teacher teacher;

    // Constructors, getters, and setters

    public Section() {
    }

    public Section(String id, String name, int capacity, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.teacher = teacher;
    }

    // Getters and setters for each field

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
