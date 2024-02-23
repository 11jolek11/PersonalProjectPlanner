package com.project.planner.controllers;

import com.project.planner.common.dto.LimitedProjectDTO;
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

    public ProjectApiController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/")
    public Project postCreateProject(@RequestBody Project project) {
        return this.projectService.createProject(project);
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
    Project putUpdateEntireProject(@PathVariable Long projectId, @RequestBody Project project) {
        return this.projectService.updateExisitngProject(projectId, project);
    }

    // TODO(11jolek11): Implement
//    @PatchMapping("/{projectId}/")
//    Project patchPartialUpdateProject(@PathVariable Long projectId, @RequestBody Map<?, ?> patch) {
//
//    }

}
