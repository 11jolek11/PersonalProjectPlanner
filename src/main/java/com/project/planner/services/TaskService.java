package com.project.planner.services;

import com.project.planner.common.AuthenticationFacadeImpl;
import com.project.planner.common.dto.TaskDTO;
import com.project.planner.common.mapper.TaskMapper;
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
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacadeImpl authentication;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, AuthenticationFacadeImpl authentication, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.authentication = authentication;
        this.taskMapper = taskMapper;
    }

    public Task findTask(Long taskId) {
        return this.taskRepository.findById(taskId).orElseThrow(() -> {
            return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "Task NOT FOUND");
        });
    }

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
        newTask = this.taskRepository.save(newTask);
        creator.getUserTasks().add(newTask);
        this.userRepository.save(creator);
        return newTask;
    }

    public void deleteTask(Long id) {
        this.taskRepository.deleteById(id);
    }

    public TaskDTO updateExistingTask(Long existingTaskId, TaskDTO taskDTO) {
        Optional<Task> oldTaskOptional = this.taskRepository.findById(existingTaskId);
        if (oldTaskOptional.isPresent()) {
            Task oldTask = oldTaskOptional.get();
            oldTask = this.taskMapper.update(taskDTO, oldTask);
            oldTask.setId(existingTaskId);
            return this.taskMapper.mapTo(this.taskRepository.save(oldTask));
        }

        throw new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "Task NOT FOUND");
    }

    public TaskStatus updateTaskStatus(Long taskId, TaskStatus newTaskStatus) {
        Task targetTask = this.findTask(taskId);
        targetTask.setTaskStatus(newTaskStatus);
        return this.taskRepository.save(targetTask).getTaskStatus();
    }

    @Transactional
    public TaskDTO replaceExistingTask(Long taskId, TaskDTO dto) {
        Optional<Task> oldTaskOptional = this.taskRepository.findById(taskId);
        if (oldTaskOptional.isPresent()) {
            Task oldTask = oldTaskOptional.get();
            this.taskMapper.update(dto, oldTask);
            return this.taskMapper.mapTo(this.taskRepository.save(oldTask));
        }

        throw new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "Task NOT FOUND");
    }

    @Transactional
    public TaskDTO replaceExistingTask(Long taskId, Task newTask) {
        Optional<Task> oldTaskOptional = this.taskRepository.findById(taskId);
        if (oldTaskOptional.isPresent()) {
            this.taskRepository.deleteById(taskId);
            newTask.setId(taskId);
            return this.taskMapper.mapTo(this.taskRepository.save(newTask));
        }

        throw new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "Task NOT FOUND");
    }
}
