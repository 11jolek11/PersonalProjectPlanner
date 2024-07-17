package com.project.planner.common.dto;

import java.util.Objects;
import java.util.Set;

public class ProjectDTO {
    private Long id;
    private String title;
    private String description;
    private Set<TaskDTO> tasks;
    private String notes;

    public ProjectDTO() {
    }

    public ProjectDTO(Long id, String title, String description, Set<TaskDTO> tasks, String notes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tasks = tasks;
        this.notes = notes;
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

    public Set<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDTO that = (ProjectDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(tasks, that.tasks) && Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, tasks, notes);
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tasks=" + tasks +
                ", notes='" + notes + '\'' +
                '}';
    }
}
