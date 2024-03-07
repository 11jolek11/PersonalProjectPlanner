package com.project.planner.controllers;

import com.project.planner.common.dto.LimitedProjectDTO;
import com.project.planner.common.mapper.ProjectMapper;
import com.project.planner.common.requests.CreateFullProjectRequest;
import com.project.planner.models.Project;
import com.project.planner.services.ProjectService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/projects/")
@Validated
public class ProjectApiController {
    // TODO(11jolek11): Add resource ownership check <-- security context to get user ID

    // For future me: Don't use following links
    // https://medium.com/@ak123aryan/how-and-where-you-can-use-preauthorize-annotation-springboot-048751193b6f
    // https://stackoverflow.com/questions/56871229/spring-security-ownership-based-access

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public ProjectApiController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @PostMapping("/")
    public Project postCreateProject(@RequestBody CreateFullProjectRequest projectRequest) {
        return this.projectService.createProject(this.projectMapper.mapFrom(projectRequest));
    }

    @GetMapping("/{projectId}/")
    public Project getProjectDetails(@PathVariable Long projectId) {
        return this.projectService.findProject(projectId);
    }

    @GetMapping("/{projectId}/limited")
    public LimitedProjectDTO getProjectLimitedView(@PathVariable Long projectId) {
        return this.projectService.mapEntityToDTO(projectId);
    }

    @DeleteMapping("/{projectId}/")
    public void deleteProjectDetails(@PathVariable Long projectId) {
        this.projectService.deleteProject(projectId);
    }

    @PutMapping("/{projectId}/")
    Project putUpdateEntireProject(@PathVariable Long projectId, @RequestBody CreateFullProjectRequest projectRequest) {
        return this.projectService.updateExisitngProject(projectId, this.projectMapper.mapFrom(projectRequest));
    }

    // TODO(11jolek11): Implement
    @PatchMapping("/{projectId}/")
    Project patchPartialUpdateProject(@PathVariable Long projectId, @RequestBody CreateFullProjectRequest projectRequest) {
        // Update only fields of Project instance (identified by projectId) which are present
        return this.projectService.updateExisitngProject(projectId, this.projectMapper.mapFrom(projectRequest));
    }
}
