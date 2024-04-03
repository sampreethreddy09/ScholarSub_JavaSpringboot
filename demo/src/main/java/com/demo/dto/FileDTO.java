package com.demo.dto;

public class FileDTO {
    private String name;
    private String path;
    private String type;

    // constructors

    public FileDTO() {
    }

    public FileDTO(String name, String path, String type) {
        this.name = name;
        this.path = path;
        this.type = type;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}