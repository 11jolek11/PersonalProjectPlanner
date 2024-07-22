package com.project.planner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

//    @NotBlank
    private String title;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL/*, mappedBy = "originProject"*/)
    @JoinColumn(name = "task_id")
    private Set<Task> tasks;
    @Lob
    private String notes;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private User maintainer;

    public Project() {
    }

    public Project(User maintainer, String title, String description, Set<Task> tasks, String notes) {
        this.maintainer = maintainer;
        this.title = title;
        this.description = description;
        this.tasks = tasks;
        this.notes = notes;
    }

    public Project(Long id, User maintainer, String title, String description, Set<Task> tasks, String notes) {
        this.id = id;
        this.maintainer = maintainer;
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

    public static ProjectBuilder builder(User maintainer, String title) {
        return new ProjectBuilder(maintainer, title);
    }

    public static class ProjectBuilder {
        private final String title;
        private String description;
        private Set<Task> tasks;
        private String notes;
        private final User maintainer;

        public ProjectBuilder(User maintainer, String title) {
            this.maintainer = maintainer;
            this.title = title;
        }

        public ProjectBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProjectBuilder notes(String notes) {
            this.notes = notes;
            return this;
        }
        public ProjectBuilder tasks(Set<Task> tasks) {
            this.tasks = tasks;
            return this;
        }

        public Project build() {
            return new Project(this.maintainer, this.title, this.description, this.tasks, this.notes);
        }
    }
}
