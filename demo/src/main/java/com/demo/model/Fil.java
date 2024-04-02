package com.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "File")
public class Fil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private Integer size;

    @Column(name = "path")
    private String path;

    // Constructors

    public Fil() {
    }

    public Fil(int id,String name, String type, int size, String path) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.path = path;
    }

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
