package com.demo.dto;

import java.util.Date;

public class AssignmentDTO {
    private int id;
    private String name;
    private Date startTime;
    private Date endTime;
    private boolean allowLateSubmission;
    private boolean scheduleRightNow;
    private String secId;
    private String description;
    private String constraints;
    private boolean inputFilesThere;
    private boolean evaluated;
    private int maxMarks;

    // constructors

    public AssignmentDTO() {
    }

    public AssignmentDTO(int id, String name, Date startTime, Date endTime,
            boolean allowLateSubmission, boolean scheduleRightNow, String secId, String description, String constraints,
            boolean inputFilesThere, boolean evaluated, int maxMarks) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.allowLateSubmission = allowLateSubmission;
        this.scheduleRightNow = scheduleRightNow;
        this.secId = secId;
        this.description = description;
        this.constraints = constraints;
        this.inputFilesThere = inputFilesThere;
        this.evaluated = evaluated;
        this.maxMarks = maxMarks;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date date) {
        this.endTime = date;
    }

    public boolean isAllowLateSubmission() {
        return allowLateSubmission;
    }

    public void setAllowLateSubmission(boolean allowLateSubmission) {
        this.allowLateSubmission = allowLateSubmission;
    }

    public boolean isScheduleRightNow() {
        return scheduleRightNow;
    }

    public void setScheduleRightNow(boolean scheduleRightNow) {
        this.scheduleRightNow = scheduleRightNow;
    }

    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public boolean isInputFilesThere() {
        return inputFilesThere;
    }

    public void setInputFilesThere(boolean inputFilesThere) {
        this.inputFilesThere = inputFilesThere;
    }

    public boolean isEvaluated() {
        return evaluated;
    }

    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }
    
}
