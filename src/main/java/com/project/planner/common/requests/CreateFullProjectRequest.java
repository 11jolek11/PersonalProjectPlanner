package com.project.planner.common.requests;

import java.util.Set;

public class CreateFullProjectRequest {
    private String title;
    private String description;
    private Set<CreateFullTaskRequest> tasks;
    private String notes;
    private Long maintainerId;

    public CreateFullProjectRequest(String title, String description, Set<CreateFullTaskRequest> tasks, String notes, Long maintainerId) {
        this.title = title;
        this.description = description;
        this.tasks = tasks;
        this.notes = notes;
        this.maintainerId = maintainerId;
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

    public Set<CreateFullTaskRequest> getTasks() {
        return tasks;
    }

    public void setTasks(Set<CreateFullTaskRequest> tasks) {
        this.tasks = tasks;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getMaintainerId() {
        return maintainerId;
    }

    public void setMaintainerId(Long maintainerId) {
        this.maintainerId = maintainerId;
    }
}
