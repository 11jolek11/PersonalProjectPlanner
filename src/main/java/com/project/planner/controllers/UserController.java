package com.project.planner.controllers;
import com.project.planner.common.dto.ProjectDTO;
import com.project.planner.common.dto.TaskDTO;
import com.project.planner.models.Project;
import com.project.planner.models.Task;
import com.project.planner.models.User;
import com.project.planner.repositories.UserRepository;
import com.project.planner.services.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/tasks")
    public Set<TaskDTO> getUserTasks(@PathVariable Long userId) {
        return userService.getUsersTasks(userId);
    }

    @GetMapping("/{userId}/projects")
    public Set<Project> getUsersProject(@PathVariable Long userId) {
        return userService.getUsersProjects(userId);
    }
}
