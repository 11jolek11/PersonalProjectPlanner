package com.project.planner.common.dto;

import com.project.planner.models.TaskStatus;

import java.time.LocalDate;
import java.util.Objects;

public class TaskDTO {
    Long id;
    String description;
    String title;
    TaskStatus taskStatus;
    String deadline;
    LocalDate createdDate;
//    Long originProjectId;
    Long ownerId;
//    String ownerEmail;

    public TaskDTO() {
    }

    public TaskDTO(Long id, Long ownerId, String deadline, TaskStatus taskStatus, String title, String description) {
        this.id = id;
        this.ownerId = ownerId;
        this.deadline = deadline;
        this.taskStatus = taskStatus;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return Objects.equals(id, taskDTO.id) && Objects.equals(description, taskDTO.description) && Objects.equals(title, taskDTO.title) && taskStatus == taskDTO.taskStatus && Objects.equals(deadline, taskDTO.deadline) && Objects.equals(createdDate, taskDTO.createdDate) && Objects.equals(ownerId, taskDTO.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, title, taskStatus, deadline, createdDate, ownerId);
    }
}