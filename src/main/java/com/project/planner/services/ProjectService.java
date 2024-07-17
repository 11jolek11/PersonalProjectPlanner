package com.project.planner.services;

import com.project.planner.common.AuthenticationFacadeImpl;
import com.project.planner.common.dto.LimitedProjectDTO;
import com.project.planner.exceptions.EntityInstanceDoesNotExist;
import com.project.planner.exceptions.ResourceDoesNotExist;
import com.project.planner.models.Project;
import com.project.planner.models.Task;
import com.project.planner.models.User;
import com.project.planner.repositories.ProjectRepository;
import com.project.planner.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacadeImpl authentication;
    private final TaskService taskService;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, AuthenticationFacadeImpl authentication, TaskService taskService) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.authentication = authentication;
        this.taskService = taskService;
    }

    public Project findProject(Long projectId) {
        return this.projectRepository.findById(projectId).orElseThrow(() -> new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND"));
    }

    public Set<Project> findAllUserProjects(User user) {
        return this.projectRepository.findProjectByMaintainer(user);
    }

    public Set<Project> findAllUserProjects(String username) {
        User maintainer = this.userRepository.findUserByEmail(username).orElseThrow(
                () -> new ResourceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND")
        );
        return this.projectRepository.findProjectByMaintainer(maintainer);
    }

    public Set<Project> findAllUserProjects() {
        User maintainer = this.userRepository.findUserByEmail(authentication.getAuthentication().getName()).orElseThrow(
                () -> new ResourceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND")
        );
        return this.projectRepository.findProjectByMaintainer(maintainer);
    }

    public Boolean exists(Long projectId) {
        return this.projectRepository.existsById(projectId);
    }

    public Project createProject(Project newProject) {
        User maintainer = this.userRepository.findUserByEmail(authentication.getAuthentication().getName())
                .orElseThrow(() -> { return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User not found"); });
        System.out.println(maintainer.toString());
        newProject.setMaintainer(maintainer);

        newProject.setTasks(newProject.getTasks().stream().map(this.taskService::createTask).collect(Collectors.toSet()));

        return this.projectRepository.save(newProject);
    }

    public void deleteProject(Long id) {
        this.projectRepository.deleteById(id);
    }

    public Project updateExisitngProject(Long existingProjectId, Project project) {
        if (this.exists(existingProjectId)) {
            Project oldProject = this.projectRepository.findById(existingProjectId).get();
            project.setId(oldProject.getId());
            project.setMaintainer(oldProject.getMaintainer());
            if (project.getTasks() == null) {
                project.setTasks(oldProject.getTasks());
            } else {
                for (Task task : project.getTasks()) {
                    task.setOwner(oldProject.getMaintainer());
                }
                project.getTasks().stream().map(this.taskService::createTask);
                Set<Task> temp = oldProject.getTasks();
                temp.addAll(project.getTasks());
                project.setTasks(temp);
            }
            return this.projectRepository.save(project);
        }

        throw new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
    }

//    public static LimitedProjectDTO mapEntityToLimitedDTO(Project project) {
//        return new LimitedProjectDTO(project.getId(), project.getTitle(), project.getDescription());
//    }

//    public LimitedProjectDTO mapEntityToLimitedDTO(Long projectId) {
//        Project project = this.findProject(projectId);
//        return new LimitedProjectDTO(project.getId(), project.getTitle(), project.getDescription());
//    }

    // TODO(11jolek11): Implement
//    public Set<FileSystemResource> getAllProjectFiles(Long projectId) throws ExecutionControl.NotImplementedException {
//        throw new ExecutionControl.NotImplementedException("Not implemented");
//    }
}
