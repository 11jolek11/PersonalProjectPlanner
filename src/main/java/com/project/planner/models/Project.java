package com.project.planner.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Project {

    @Id
    @SequenceGenerator(name = "projectIdGen", sequenceName = "projectIdSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectIdGen")
    private Long id;

    @NotBlank
    private String title;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "originProject")
    private Set<Task> tasks;
    @Lob
    private String notes;

    @OneToOne(mappedBy = "project", optional = false, cascade = CascadeType.ALL)
    private User maintainer;

    public Project() {
    }

    public Project(User maintainer, String title, String description) {
        this.maintainer = maintainer;
        this.title = title;
        this.description = description;
    }

    public Project(User maintainer, String title, String description, Set<Task> tasks, String notes) {
        this.maintainer = maintainer;
        this.title = title;
        this.description = description;
        this.tasks = tasks;
        this.notes = notes;
    }

    public User getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(User maintainer) {
        this.maintainer = maintainer;
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
        return Objects.equals(id, project.id) && Objects.equals(title, project.title) && Objects.equals(description, project.description) && Objects.equals(tasks, project.tasks) && Objects.equals(notes, project.notes) && Objects.equals(maintainer, project.maintainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, tasks, notes, maintainer);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tasks=" + tasks +
                ", notes='" + notes + '\'' +
                ", maintainer=" + maintainer +
                '}';
    }
}
