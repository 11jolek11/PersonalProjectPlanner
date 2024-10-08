package com.project.planner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @SequenceGenerator(name = "taskIdGen", initialValue = 0, allocationSize = 1, sequenceName = "taskIdSeq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskIdGen")
    private Long id;
    @NotBlank
    @NotEmpty
    private String title;
    private String description;
//    @Lob
    private String notes;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @CreatedDate
    private LocalDate createdDate;

    private LocalDate deadline;

    @ManyToOne(optional = false)
    @JoinColumn(name="owner")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User owner;

    public Task() {
    }

    public Task(String title, String description, String notes, TaskStatus taskStatus, LocalDate deadline, /* Project originProject, */ User owner) {
        this.title = title;
        this.description = description;
        this.notes = notes;
        this.taskStatus = taskStatus;
        this.deadline = deadline;
        this.owner = owner;
    }

    public Task(Long id, String title, String description, String notes, TaskStatus taskStatus, LocalDate deadline, /* Project originProject, */ User owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.notes = notes;
        this.taskStatus = taskStatus;
        this.deadline = deadline;

        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(notes, task.notes) && taskStatus == task.taskStatus && Objects.equals(createdDate, task.createdDate) && Objects.equals(deadline, task.deadline) && Objects.equals(owner, task.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, notes, taskStatus, createdDate, deadline, owner);
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
                ", owner=" + owner +
                '}';
    }

    public static TaskBuilder builder(String taskTitle, User owner) {
        return new TaskBuilder(taskTitle, owner);
    }

    public static class TaskBuilder {
        // Using builder to avoid telescopic constructors
        private final String title;
        private String description;
        private String notes;
        private TaskStatus taskStatus = TaskStatus.UNKOWN;
        private LocalDate deadline;
        private final User owner;

        public TaskBuilder(String taskTitle, User owner) {
            this.title = taskTitle;
            this.owner = owner;
        }

        public TaskBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public TaskBuilder taskStatus(TaskStatus taskStatus) {
            this.taskStatus = taskStatus;
            return this;
        }

        public TaskBuilder deadline(LocalDate deadline) {
            this.deadline = deadline;
            return this;
        }

        public Task build() {
            return new Task(this.title, this.description,
                    this.notes, this.taskStatus, this.deadline,
                    this.owner);
        }
    }
}
