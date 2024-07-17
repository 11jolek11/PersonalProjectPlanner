package com.project.planner.services;

import com.project.planner.common.AuthenticationFacadeImpl;
import com.project.planner.exceptions.EntityInstanceDoesNotExist;
import com.project.planner.exceptions.ResourceDoesNotExist;
import com.project.planner.models.Task;
import com.project.planner.models.TaskStatus;
import com.project.planner.models.User;
import com.project.planner.repositories.ProjectRepository;
import com.project.planner.repositories.TaskRepository;
import com.project.planner.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacadeImpl authentication;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository, AuthenticationFacadeImpl authentication) {
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

//    public Set<Task> findTasks(Project originProject) {
//        return this.taskRepository.findTasksByOriginProject(originProject);
//    }

    public Set<Task> findTasks(User owner) {
        return this.taskRepository.findTasksByOwner(owner);
    }

    public Boolean exists(Long id) {
        return this.taskRepository.existsById(id);
    }

    public Task createTask(Task newTask) {
        String creatorEmail = authentication.getAuthentication().getName();
        User creator = this.userRepository.findUserByEmail(creatorEmail)
                .orElseThrow(() -> { return new ResourceDoesNotExist(HttpStatus.NOT_FOUND, "User not found"); });
        newTask.setOwner(creator);
        System.out.println("Task created by: ".concat(creatorEmail));
        return this.taskRepository.save(newTask);
    }

    public void deleteTask(Long id) {
        this.taskRepository.deleteById(id);
    }

    public Task updateExistingTask(Long existingTaskId, Task task) {
        if (this.exists(existingTaskId)) {
            User owner = this.taskRepository.findById(existingTaskId).get().getOwner();
            task.setId(existingTaskId);
            if (task.getOwner() == null) {
                task.setOwner(owner);
            }
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

    public TaskStatus updateTaskStatus(Long taskId, TaskStatus newTaskStatus) {
        // TODO(11jolek11): Can I simplify this procedure?
        Task targetTask = this.findTask(taskId);
        targetTask.setTaskStatus(newTaskStatus);
        return this.taskRepository.save(targetTask).getTaskStatus();
    }
}
