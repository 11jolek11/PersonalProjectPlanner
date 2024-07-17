package com.project.planner.controllers;

import com.project.planner.common.dto.LimitedProjectDTO;
import com.project.planner.common.dto.ProjectDTO;
import com.project.planner.common.mapper.LimitedProjectMapper;
import com.project.planner.common.mapper.ProjectMapper;
import com.project.planner.models.Project;
import com.project.planner.services.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/projects")
@Validated
public class ProjectApiController {
    // TODO(11jolek11): Add resource ownership check <-- security context to get user ID

    // For future me: Don't use following links
    // https://medium.com/@ak123aryan/how-and-where-you-can-use-preauthorize-annotation-springboot-048751193b6f
    // https://stackoverflow.com/questions/56871229/spring-security-ownership-based-access

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final LimitedProjectMapper limitedProjectMapper;
    private final ModelMapper modelMapper;

    public ProjectApiController(ProjectService projectService, ProjectMapper projectMapper, LimitedProjectMapper limitedProjectMapper,
                                ModelMapper modelMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
        this.limitedProjectMapper = limitedProjectMapper;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public Set<ProjectDTO> getAllProjects() {
        return this.projectService.findAllUserProjects().stream()
                .map((element) -> modelMapper.map(element, ProjectDTO.class))
                .collect(Collectors.toSet());
    }

    @PostMapping("")
    public ProjectDTO createProject(@RequestBody ProjectDTO projectRequest) {
        System.out.println("POST CREATE PROJECT");
        Project newProject =  this.projectService.createProject(this.projectMapper.mapFrom(projectRequest));
        return this.projectMapper.mapTo(newProject);
    }

    @GetMapping("/{projectId}")
    public ProjectDTO getProjectDetails(@PathVariable Long projectId) {
        return this.projectMapper.mapTo(this.projectService.findProject(projectId));
    }

    @GetMapping("/{projectId}/limited")
    public LimitedProjectDTO getProjectLimitedView(@PathVariable Long projectId) {
        Project targetProject = this.projectService.findProject(projectId);
        return limitedProjectMapper.mapTo(targetProject);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Map<String, String>> deleteProject(@PathVariable Long projectId) {
        this.projectService.deleteProject(projectId);

        return ResponseEntity.ok(Map.of("status", "deleted"));
    }
//    @PutMapping("/{projectId}")
//    Project updateEntireProject(@PathVariable Long projectId, @RequestBody ProjectDTO projectRequest) {
//        return this.projectService.updateExisitngProject(projectId, this.projectMapper.mapFrom(projectRequest));
//    }
//    @PatchMapping("/{projectId}")
//    Project partialUpdateProject(@PathVariable Long projectId, @RequestBody ProjectDTO projectRequest) {
//        // Update only fields of Project instance (identified by projectId) which are present
//        return this.projectService.updateExisitngProject(projectId, this.projectMapper.mapFrom(projectRequest));
//    }
}
