package com.project.planner.controllers;

import com.project.planner.models.Project;
import com.project.planner.repositories.ProjectRepository;
import com.project.planner.services.ProjectService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/projects/")
@Validated
public class ProjectApiController {
    // TODO(11jolek11): Add resource ownership check <-- security context to get user ID

    // For future me: Don't use following links
    // https://medium.com/@ak123aryan/how-and-where-you-can-use-preauthorize-annotation-springboot-048751193b6f
    // https://stackoverflow.com/questions/56871229/spring-security-ownership-based-access

    private ProjectService projectService;
    private ProjectRepository projectRepository;

    public ProjectApiController(ProjectService projectService, ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    @PostMapping("/")
    public Project createProject(@RequestBody Project project) {
        return this.projectRepository.save(project);
    }

    @GetMapping("/{projectId}/")
    public Project projectDetails(@PathVariable Long projectId) {

    }

    @DeleteMapping("/{projectId}/")
    public Project projectDetails(@PathVariable Long projectId) {

    }

    @PutMapping("/{projectId}/")
    Project updateEntireProject(@RequestBody Project project) {

    }

    @PatchMapping("/{projectId}/")
    Project partialUpdateProject(@PathVariable Long projectId, @RequestBody Map<?, ?> patch) {

    }

}
