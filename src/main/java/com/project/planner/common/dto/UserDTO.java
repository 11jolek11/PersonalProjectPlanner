package com.project.planner.common.dto;

import java.util.Objects;
import java.util.Set;


public class UserDTO {
    String email;
    Set<LimitedProjectDTO> projects;
    Set<TaskDTO> userTasks;

    public UserDTO() {
    }

    public UserDTO(String email, Set<LimitedProjectDTO> projects, Set<TaskDTO> userTasks) {
        this.email = email;
        this.projects = projects;
        this.userTasks = userTasks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<LimitedProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(Set<LimitedProjectDTO> projects) {
        this.projects = projects;
    }

    public Set<TaskDTO> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(Set<TaskDTO> userTasks) {
        this.userTasks = userTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(email, userDTO.email) && Objects.equals(projects, userDTO.projects) && Objects.equals(userTasks, userDTO.userTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, projects, userTasks);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", projects=" + projects +
                ", userTasks=" + userTasks +
                '}';
    }
}

