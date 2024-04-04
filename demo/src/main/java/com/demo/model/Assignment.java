package com.demo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "allow_late_submission")
    private boolean allowLateSubmission;

    @Column(name = "schedule_right_now")
    private boolean scheduleRightNow;

    @ManyToOne
    @JoinColumn(name = "sec_id")
    private Section section;

    @Column(name = "description")
    private String description;

    @Column(name = "constraints")
    private String constraints;

    @Column(name = "input_files_there")
    private boolean inputFilesThere;

    @Column(name = "is_evaluated")
    private boolean evaluated;

    @Column(name = "max_marks")
    private int maxMarks;

    // constructors

    public Assignment() {
    }

    public Assignment(int id, String name, Date startTime, Date endTime, boolean allowLateSubmission,
            boolean scheduleRightNow, Section section, String description, String constraints, boolean inputFilesThere,
            boolean evaluated, int maxMarks) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.allowLateSubmission = allowLateSubmission;
        this.scheduleRightNow = scheduleRightNow;
        this.section = section;
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

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
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
