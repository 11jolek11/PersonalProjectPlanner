package com.project.planner.common.dto;

import com.project.planner.models.TaskStatus;

import java.time.LocalDate;
import java.util.Objects;

//public record TaskDTO (Long id, String description, String title, TaskStatus taskStatus,
//                       LocalDate deadline, LocalDate createdDate, Long originProjectId,
//                       Long ownerId, String owner) {
//}

public class TaskDTO {
    Long id;
    String description;
    String title;
    TaskStatus taskStatus;
    LocalDate deadline;
    LocalDate createdDate;
//    Long originProjectId;
    Long ownerId;
    String owner;

    public TaskDTO() {
    }

    public TaskDTO(String owner, Long ownerId, /*Long originProjectId,*/ LocalDate createdDate, LocalDate deadline, TaskStatus taskStatus, String title, String description, Long id) {
        this.owner = owner;
        this.ownerId = ownerId;
//        this.originProjectId = originProjectId;
        this.createdDate = createdDate;
        this.deadline = deadline;
        this.taskStatus = taskStatus;
        this.title = title;
        this.description = description;
        this.id = id;
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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

//    public Long getOriginProjectId() {
//        return originProjectId;
//    }
//
//    public void setOriginProjectId(Long originProjectId) {
//        this.originProjectId = originProjectId;
//    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return Objects.equals(id, taskDTO.id) && Objects.equals(description, taskDTO.description) && Objects.equals(title, taskDTO.title) && taskStatus == taskDTO.taskStatus && Objects.equals(deadline, taskDTO.deadline) && Objects.equals(createdDate, taskDTO.createdDate) /*&& Objects.equals(originProjectId, taskDTO.originProjectId) */&& Objects.equals(ownerId, taskDTO.ownerId) && Objects.equals(owner, taskDTO.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, title, taskStatus, deadline, createdDate, /*originProjectId,*/ ownerId, owner);
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", taskStatus=" + taskStatus +
                ", deadline=" + deadline +
                ", createdDate=" + createdDate +
//                ", originProjectId=" + originProjectId +
                ", ownerId=" + ownerId +
                ", owner='" + owner + '\'' +
                '}';
    }
}