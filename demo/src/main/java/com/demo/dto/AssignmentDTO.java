package com.demo.dto;

import java.time.LocalDateTime;

public class AssignmentDTO {
    private int id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean allowLateSubmission;
    private boolean scheduleRightNow;
    private String secId;

    public AssignmentDTO() {
    }

    public AssignmentDTO(int id, String name, LocalDateTime startTime, LocalDateTime endTime, boolean allowLateSubmission,
            boolean scheduleRightNow, String secId) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.allowLateSubmission = allowLateSubmission;
        this.scheduleRightNow = scheduleRightNow;
        this.secId = secId;
    }

    // Getters and setters

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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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

    

    
}
