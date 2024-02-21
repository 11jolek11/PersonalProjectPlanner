package com.project.planner.services;

import com.project.planner.exceptions.EntityInstanceDoesNotExist;
import com.project.planner.models.Project;
import com.project.planner.models.User;
import com.project.planner.repositories.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserService userService;

    public ProjectService(ProjectRepository projectRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    public Long initEmptyProject(User newMaintainer, String newTitle, String newDescription) {
        // TODO(11jolek11): Is this needed?
        if (this.userService.checkIfUserExists(newMaintainer.getUsername())) {
            return this.projectRepository
                    .save(
                    Project.builder(newMaintainer, newTitle)
                            .description(newDescription)
                            .build()
                    ).getId();
        } else {
            throw new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
        }
    }

    public Long changeMaintainer(Long projectId, User newMaintainer) {
        if (this.userService.checkIfUserExists(newMaintainer.getUsername())) {
            throw new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
        }

        Project targetProject = this.projectRepository.findById(projectId).orElseThrow(() -> {
            return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
        });

        targetProject.setMaintainer(newMaintainer);
        return this.projectRepository.save(targetProject).getId();
    }
    // TODO(11jolek11): Add option to add Task for projects
}
