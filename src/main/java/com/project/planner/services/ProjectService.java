package com.project.planner.services;

import com.project.planner.exceptions.EntityInstanceDoesNotExist;
import com.project.planner.models.Project;
import com.project.planner.models.User;
import com.project.planner.repositories.ProjectRepository;
import com.project.planner.repositories.UserRepository;
import org.springframework.http.HttpStatus;

public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    private Boolean checkIfUserExists(Long id) {
        return this.userRepository.existsById(id);
    }

    private Boolean checkIfUserExists(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public Long addProject(Project project) {
        return this.projectRepository.save(project).getId();
    }

    public Long initEmptyProject(User newMaintainer, String newTitle, String newDescription) {
        if (this.checkIfUserExists(newMaintainer.getUsername())) {
            return this.projectRepository.save(new Project(newMaintainer, newTitle, newDescription)).getId();
        } else {
            throw new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
        }
    }

    public Long changeMaintainer(Long projectId, User newMaintainer) {
        if (this.checkIfUserExists(newMaintainer.getUsername())) {
            throw new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
        }

        Project targetProject = this.projectRepository.findById(projectId).orElseThrow(() -> {
            return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
        });

        targetProject.setMaintainer(newMaintainer);
        return this.projectRepository.save(targetProject).getId();
    }

    public Long changeTitle(Long projectId, String newTitle) {
        Project targetProject = this.projectRepository.findById(projectId).orElseThrow(() -> {
            return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
        });

        targetProject.setTitle(newTitle);
        return this.projectRepository.save(targetProject).getId();
    }

    public Long updateNotes(Long projectId, String newNotes) {
        Project targetProject = this.projectRepository.findById(projectId).orElseThrow(() -> {
            return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
        });

        targetProject.setNotes(newNotes);
        return this.projectRepository.save(targetProject).getId();
    }

    public Long changeDescription(Long projectId, String newDescription) {
        Project targetProject = this.projectRepository.findById(projectId).orElseThrow(() -> {
            return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
        });

        targetProject.setDescription(newDescription);
        return this.projectRepository.save(targetProject).getId();
    }

    public void deleteProject(Project project) {
        this.projectRepository.delete(project);
    }

    public void deleteProject(Long id) {
        this.projectRepository.deleteById(id);
    }
}
