package com.project.planner.common.requests;

import com.project.planner.models.TaskStatus;
import java.time.LocalDate;

public class CreateFullTaskRequest {
    private String title;
    private String description;
    private String notes;
    private TaskStatus taskStatus;
    private LocalDate deadline;
    private Long originProjectId;

    public CreateFullTaskRequest() {
    }

    public CreateFullTaskRequest(String title, String description, String notes, TaskStatus taskStatus, LocalDate deadline, Long originProjectId) {
        this.title = title;
        this.description = description;
        this.notes = notes;
        this.taskStatus = taskStatus;
        this.deadline = deadline;
        this.originProjectId = originProjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Long getOriginProjectId() {
        return originProjectId;
    }

    public void setOriginProjectId(Long originProjectId) {
        this.originProjectId = originProjectId;
    }
}
