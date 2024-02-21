package com.project.planner.services;

import com.project.planner.exceptions.EntityInstanceDoesNotExist;
import com.project.planner.models.Project;
import com.project.planner.models.Task;
import com.project.planner.models.TaskStatus;
import com.project.planner.repositories.ProjectRepository;
import com.project.planner.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public Long addTask(Task newTask) {
        return this.taskRepository.save(newTask).getId();
    }

    public Long updateTaskNotes(Long taskId, String newNotes) {
        Task targetTask = this.taskRepository.findById(taskId).orElseThrow(() -> {
            return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "Task NOT FOUND");
        });

        targetTask.setNotes(newNotes);
        return this.taskRepository.save(targetTask).getId();
    }

    public Long updateTaskStatus(Long taskId, TaskStatus newTaskStatus) {
        Task targetTask = this.taskRepository.findById(taskId).orElseThrow(() -> {
            return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "Task NOT FOUND");
        });

        targetTask.setTaskStatus(newTaskStatus);
        return this.taskRepository.save(targetTask).getId();
    }

    public Long attachTaskToProject(Task newTask, Project existingProject) {
        Set<Task> taskSet = existingProject.getTasks();
        taskSet.add(newTask);
        existingProject.setTasks(taskSet);

        return this.projectRepository.save(existingProject).getId();
    }
    public Long attachTaskToProject(Task newTask, Long existingProjectId) {
        Project existingProject = this.projectRepository.findById(existingProjectId).orElseThrow(() -> {
            return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "Project NOT FOUND");
        });

        Set<Task> taskSet = existingProject.getTasks();
        taskSet.add(newTask);
        existingProject.setTasks(taskSet);

        return this.projectRepository.save(existingProject).getId();
    }
}
