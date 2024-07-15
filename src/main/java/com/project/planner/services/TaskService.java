package com.project.planner.services;

import com.project.planner.common.AuthenticationFacade;
import com.project.planner.exceptions.EntityInstanceDoesNotExist;
import com.project.planner.models.Project;
import com.project.planner.models.Task;
import com.project.planner.models.TaskStatus;
import com.project.planner.models.User;
import com.project.planner.repositories.ProjectRepository;
import com.project.planner.repositories.TaskRepository;
import com.project.planner.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Stream;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authentication;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository, AuthenticationFacade authentication) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.authentication = authentication;
    }

    public Task findTask(Long taskId) {
        return this.taskRepository.findById(taskId).orElseThrow(() -> {
            return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "Task NOT FOUND");
        });
    }

    public Set<Task> findTasks(Project originProject) {
        return this.taskRepository.findTasksByOriginProject(originProject);
    }

    public Set<Task> findTasks(User owner) {
        return this.taskRepository.findTasksByOwner(owner);
    }

    public Boolean exists(Long id) {
        return this.taskRepository.existsById(id);
    }

    public Task createTask(Task newTask) {
        return this.taskRepository.save(newTask);
    }

    public void deleteTask(Long id) {
        this.taskRepository.deleteById(id);
    }

    public Task updateExistingTask(Long existingTaskId, Task task) {
        if (this.exists(existingTaskId) && task.getId().equals(existingTaskId)) {
            return this.taskRepository.save(task);
        }

        throw new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "Task NOT FOUND");
    }

    // TODO(11jolek11): Implement
//    public Set<FileSystemResource> getTaskFiles(Long taskId) {
//
//    }
    // TODO(11jolek11): Implement
//    public Long setTaskFile(Long taskId, String filename) {
//
//    }

    public Task updateTaskStatus(Long taskId, TaskStatus newTaskStatus) {
        // TODO(11jolek11): Can I simplify this procedure?
        Task targetTask = this.findTask(taskId);
        targetTask.setTaskStatus(newTaskStatus);
        return this.taskRepository.save(targetTask);
    }
}
