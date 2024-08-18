package com.project.planner.controllers;

import com.project.planner.common.dto.TaskDTO;
import com.project.planner.common.mapper.TaskMapper;
import com.project.planner.models.Task;
import com.project.planner.models.TaskStatus;
import com.project.planner.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tasks")
@Validated
public class TaskApiController {
    // TODO(11jolek11): Add resource ownership check <-- security context to get user ID

    // For future me: Don't use following links
    // https://medium.com/@ak123aryan/how-and-where-you-can-use-preauthorize-annotation-springboot-048751193b6f
    // https://stackoverflow.com/questions/56871229/spring-security-ownership-based-access

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskApiController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping("")
    public TaskDTO createTask(@RequestBody TaskDTO taskRequest) {

        Task newTask = this.taskService.createTask(this.taskMapper.mapFrom(taskRequest));
        return this.taskMapper.mapTo(newTask);
    }

    @GetMapping("/{taskId}")
    public TaskDTO getTaskDetails(@PathVariable Long taskId) {
        return this.taskMapper.mapTo(this.taskService.findTask(taskId));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable Long taskId) {
        this.taskService.deleteTask(taskId);
        return ResponseEntity.ok(Map.of("status", "deleted"));
    }

    @PutMapping("/{taskId}")
    public TaskDTO updateEntireTask(@PathVariable Long taskId, @RequestBody TaskDTO dto) {
        return this.taskService.replaceExistingTask(taskId, dto);
    }
    @PatchMapping("/{taskId}")
    public TaskDTO partialUpdateTask(@PathVariable Long taskId, @RequestBody TaskDTO dto) {
        // Update only fields of Task instance (identified by taskId) which are present
        return this.taskService.updateExistingTask(taskId, dto);
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<Map<Long, String>> changeTaskStatus(@PathVariable Long taskId, @RequestParam(name = "status") TaskStatus taskStatus) {
        Map<Long, String> map = Map.of(taskId, this.taskService.updateTaskStatus(taskId, taskStatus).name());
        return ResponseEntity.ok(map);
    }
}
