package com.demo.dto;

public class createAsFileDTO {
    
    private String name;
    private Integer size;
    private String type;


    
    public createAsFileDTO() {
    }
    public createAsFileDTO(String name, Integer size, String type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    
}
