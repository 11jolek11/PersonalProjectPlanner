package com.project.planner.services;

import com.project.planner.common.dto.ProjectDTO;
import com.project.planner.common.dto.TaskDTO;
import com.project.planner.common.mapper.ProjectMapper;
import com.project.planner.common.mapper.TaskMapper;
import com.project.planner.exceptions.EntityInstanceDoesNotExist;
import com.project.planner.exceptions.UserAlreadyExistsException;
import com.project.planner.models.Project;
import com.project.planner.models.Task;
import com.project.planner.models.User;
import com.project.planner.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    private static final String USER_NOT_FOUND_MESSAGE = "User not found";

    public UserService(UserRepository userRepository, TaskMapper taskMapper) {
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
    }

    public Boolean checkIfUserExists(Long id) {
        return this.userRepository.existsById(id);
    }

    public Boolean checkIfUserExists(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User getUser(String email) {

        return this.userRepository.findUserByEmail(email).orElseThrow(
                () -> new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, USER_NOT_FOUND_MESSAGE)
        );
    }

    protected User registerNewUser(User newUser) throws UserAlreadyExistsException {
        if (this.checkIfUserExists(newUser.getUsername())) {
            // TODO(11jolek11): Find better way to handle this type of event
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST, "User already exists, try to login");
        } else {
            return this.userRepository.save(newUser);
        }
    }

    public Boolean checkIfUserHaveFullRights(Long id) {
        User targetUser = this.userRepository.findById(id).orElseThrow(() -> new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, USER_NOT_FOUND_MESSAGE));

        return targetUser.isEnabled() && targetUser.isAccountNonLocked();
    }

    public Boolean checkIfUserHaveFullRights(String email) {
        User targetUser = this.userRepository.findUserByEmail(email).orElseThrow(() -> new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, USER_NOT_FOUND_MESSAGE));

        return targetUser.isEnabled() && targetUser.isAccountNonLocked();
    }

    public Set<TaskDTO> getUsersTasks(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, USER_NOT_FOUND_MESSAGE)
        );

        return user.getUserTasks().stream().map(this.taskMapper::mapTo).collect(Collectors.toSet());
    }

    public Set<Project> getUsersProjects(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, USER_NOT_FOUND_MESSAGE)
        );

        return user.getProjects();
    }
}
