package com.project.planner.services;

import com.project.planner.common.dto.LimitedProjectDTO;
import com.project.planner.exceptions.EntityInstanceDoesNotExist;
import com.project.planner.models.Project;
import com.project.planner.repositories.ProjectRepository;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project findProject(Long projectId) {
        return this.projectRepository.findById(projectId).orElseThrow(() -> {
            return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
        });
    }

    // TODO(11jolek11): Not sure if it is a good idea
    public static LimitedProjectDTO mapEntityToDTO(Project project) {
        return new LimitedProjectDTO(project.getId(), project.getTitle(), project.getTasks().size());
    }

    public LimitedProjectDTO mapEntityToDTO(Long projectId) {
        Project project = this.findProject(projectId);
        return new LimitedProjectDTO(project.getId(), project.getTitle(), project.getTasks().size());
    }

    // TODO(11jolek11): Implement
    public Set<FileSystemResource> getAllProjectFiles(Long projectId) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented");
    }
}
