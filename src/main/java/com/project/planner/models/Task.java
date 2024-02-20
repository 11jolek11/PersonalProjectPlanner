package com.project.planner.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @SequenceGenerator(name = "taskIdGen", initialValue = 0, allocationSize = 1, sequenceName = "taskIdSeq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskIdGen")
    private Long id;
    @NotBlank
    private String title;
    private String description;
    @Lob
    private String notes;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @CreatedDate
    private LocalDate createdDate;

    private LocalDate deadline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "origin_project")
    private Project originProject;

    public Task() {
    }

    public Task(Long id, String title, String description, TaskStatus taskStatus, Project originProject) {
        // TODO(11jolek11): Change constructor to builder pattern
        this.id = id;
        this.title = title;
        this.description = description;
        this.taskStatus = taskStatus;
        this.originProject = originProject;
    }

    public Task(Long id, String title, String description, String notes, TaskStatus taskStatus, LocalDate createdDate, LocalDate deadline, Project originProject) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.notes = notes;
        this.taskStatus = taskStatus;
        this.createdDate = createdDate;
        this.deadline = deadline;
        this.originProject = originProject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getOriginProject() {
        return originProject;
    }

    public void setOriginProject(Project originProject) {
        this.originProject = originProject;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(notes, task.notes) && taskStatus == task.taskStatus && Objects.equals(createdDate, task.createdDate) && Objects.equals(deadline, task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, notes, taskStatus, createdDate, deadline);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", notes='" + notes + '\'' +
                ", taskStatus=" + taskStatus +
                ", createdDate=" + createdDate +
                ", deadline=" + deadline +
                '}';
    }
}
