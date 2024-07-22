package com.project.planner.services;

import com.project.planner.common.AuthenticationFacadeImpl;
import com.project.planner.common.dto.ProjectDTO;
import com.project.planner.common.mapper.ProjectMapper;
import com.project.planner.exceptions.EntityInstanceDoesNotExist;
import com.project.planner.exceptions.ResourceDoesNotExist;
import com.project.planner.models.Project;
import com.project.planner.models.Task;
import com.project.planner.models.User;
import com.project.planner.repositories.ProjectRepository;
import com.project.planner.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacadeImpl authentication;
    private final TaskService taskService;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, AuthenticationFacadeImpl authentication, TaskService taskService, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.authentication = authentication;
        this.taskService = taskService;
        this.projectMapper = projectMapper;
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

    @Transactional
    public Project createProject(Project newProject) {
        User maintainer = this.userRepository.findUserByEmail(authentication.getAuthentication().getName())
                .orElseThrow(() -> { return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User not found"); });
        System.out.println(maintainer.toString());
        newProject.setMaintainer(maintainer);
        Task[] tempTasks = new Task[0];
        tempTasks = newProject.getTasks().toArray(tempTasks);

        for (int i = 0; i < tempTasks.length; i++) {
            Task task = tempTasks[i];
            task.setOwner(maintainer);
            tempTasks[i] = this.taskService.createTask(task);
        }
        newProject.setTasks(Set.of(tempTasks));

        Set<Project> newProjects = new HashSet<>(maintainer.getProjects());
        newProjects.add(newProject);
        maintainer.setProjects(newProjects);
        Long maintainerId = maintainer.getId();
        User tempUser = this.userRepository.save(maintainer);
        assert (Objects.equals(tempUser.getId(), maintainerId));

        this.userRepository.save(maintainer);
        return this.projectRepository.save(newProject);
    }

    @Transactional
    public void deleteProject(Long id) {
        Project project = this.projectRepository.findById(id).orElseThrow(
                () -> { return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "Project not found"); }
        );
        User maintainer = project.getMaintainer();
        Set<Project> projects = maintainer.getProjects();
        projects.remove(project);
        maintainer.setProjects(projects);
        this.projectRepository.deleteById(id);
    }

    public ProjectDTO updateExistingProject(Long existingProjectId, ProjectDTO projectDTO) {
        Optional<Project> oldProjectOptional = this.projectRepository.findById(existingProjectId);
        if (oldProjectOptional.isPresent()) {
            Project oldProject = oldProjectOptional.get();
            oldProject = this.projectMapper.update(projectDTO, oldProject);
            oldProject.setId(existingProjectId);
            return this.projectMapper.mapTo(this.projectRepository.save(oldProject));
        }

        throw new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
    }
    @Transactional
    public ProjectDTO replaceExistingProject(Long existingProjectId, ProjectDTO projectDTO) {
        Optional<Project> oldProjectOptional = this.projectRepository.findById(existingProjectId);
        if (oldProjectOptional.isPresent()) {
            User maintainer = this.userRepository.findUserByEmail(authentication.getAuthentication().getName())
                    .orElseThrow(() -> { return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User not found"); });

            Project oldProject = oldProjectOptional.get();
            this.projectMapper.update(projectDTO, oldProject);
            return this.projectMapper.mapTo(this.projectRepository.save(oldProject));
        }

        throw new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
    }
}
