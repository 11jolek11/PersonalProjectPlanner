package com.project.planner.common.dto;

import java.util.Objects;

public class LimitedProjectDTO {

    private Long id;
    private String title;
    private Integer numberOfTasks;

    public LimitedProjectDTO() {
    }
    public LimitedProjectDTO(Long id, String title, Integer numberOfTasks) {
        this.id = id;
        this.title = title;
        this.numberOfTasks = numberOfTasks;
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

    public Integer getNumberOfTasks() {
        return numberOfTasks;
    }

    public void setNumberOfTasks(Integer numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LimitedProjectDTO that = (LimitedProjectDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(numberOfTasks, that.numberOfTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, numberOfTasks);
    }

    @Override
    public String toString() {
        return "LimitedProjectDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", numberOfTasks=" + numberOfTasks +
                '}';
    }
}
