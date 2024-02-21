package com.project.planner.services;

import com.project.planner.exceptions.EntityInstanceDoesNotExist;
import com.project.planner.models.Task;
import com.project.planner.models.TaskStatus;
import com.project.planner.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTask(Long taskId) {
        return this.taskRepository.findById(taskId).orElseThrow(() -> {
            return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "Task NOT FOUND");
        });
    }

    // TODO(11jolek11): Implement
//    public Set<FileSystemResource> getTaskFiles(Long taskId) {
//
//    }
    // TODO(11jolek11): Implement
//    public Long setTaskFile(Long taskId, String filename) {
//
//    }



    public Long updateTaskStatus(Long taskId, TaskStatus newTaskStatus) {
        Task targetTask = this.getTask(taskId);
        targetTask.setTaskStatus(newTaskStatus);
        return this.taskRepository.save(targetTask).getId();
    }
}
