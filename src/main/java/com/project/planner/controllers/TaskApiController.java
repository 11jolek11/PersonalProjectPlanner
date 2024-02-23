package com.project.planner.controllers;

import com.project.planner.models.Task;
import com.project.planner.models.TaskStatus;
import com.project.planner.services.TaskService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/tasks/")
@Validated
public class TaskApiController {
    // TODO(11jolek11): Add resource ownership check <-- security context to get user ID

    // For future me: Don't use following links
    // https://medium.com/@ak123aryan/how-and-where-you-can-use-preauthorize-annotation-springboot-048751193b6f
    // https://stackoverflow.com/questions/56871229/spring-security-ownership-based-access

    private final TaskService taskService;

    public TaskApiController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/")
    public Task createTask(@RequestBody Task task) {
        return this.taskService.createTask(task);
    }

    @GetMapping("/{taskId}/")
    public Task getTaskDetails(@PathVariable Long taskId) {
        return this.taskService.findTask(taskId);
    }

    @DeleteMapping("/{taskId}/")
    public void deleteTask(@PathVariable Long taskId) {
        this.taskService.deleteTask(taskId);
    }

    @PutMapping("/{taskId}/")
    public Task putUpdateEntireTask(@PathVariable Long taskId, @RequestBody Task task) {
        return this.taskService.updateExistingTask(taskId, task);
    }

//    @PatchMapping("/{taskId}/")
//    Task partialUpdateTask(@RequestBody Map<?, ?> patch) {
//
//    }

    @PatchMapping("/{taskId}/")
    public Task patchChangeTaskStatus(@PathVariable Long taskId, @RequestParam(name = "status") TaskStatus taskStatus) {
        // Simplified alternative to PATCH partialUpdateTask with task status in json body
        // Rationale: Changing tasks status should be easy and quick for frontend devs
        return this.taskService.updateTaskStatus(taskId, taskStatus);
    }
}
