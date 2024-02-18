package com.project.planner.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Project {

    @Id
    @SequenceGenerator(name = "projectIdGen", sequenceName = "projectIdSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectIdGen")
    private Long id;

    private String title;
    private String description;

    @OneToMany
    private Set<Task> tasks;
    @Lob
    private String notes;

    public Project() {
    }

    public Project(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Project(Long id, String title, String description, Set<Task> tasks, String notes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tasks = tasks;
        this.notes = notes;
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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
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
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(title, project.title) && Objects.equals(description, project.description) && Objects.equals(tasks, project.tasks) && Objects.equals(notes, project.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, tasks, notes);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tasks=" + tasks +
                ", notes='" + notes + '\'' +
                '}';
    }
}
