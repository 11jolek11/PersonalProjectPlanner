package com.project.planner.services;

import com.project.planner.exceptions.EntityInstanceDoesNotExist;
import com.project.planner.exceptions.UserAlreadyExistsException;
import com.project.planner.models.User;
import com.project.planner.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean checkIfUserExists(Long id) {
        return this.userRepository.existsById(id);
    }

    public Boolean checkIfUserExists(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User getUser(String email) {

        return this.userRepository.findUserByEmail(email).orElseThrow(
                () -> new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND")
        );
    }

    protected User registerNewUser(User newUser) throws UserAlreadyExistsException {
        if (this.checkIfUserExists(newUser.getUsername())) {
            // TODO(11jolek11): Find better way to handle this type of event
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST, "User already exists, try to login");
        } else {
            System.out.println("Trying to register new User with { name: " + newUser.getUsername() + ", password: " + newUser.getPassword());
            return this.userRepository.save(newUser);
        }
    }

    public Boolean checkIfUserHaveFullRights(Long id) {
        User targetUser = this.userRepository.findById(id).orElseThrow(() -> new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND"));

        return targetUser.isEnabled() && targetUser.isAccountNonLocked();
    }

    public Boolean checkIfUserHaveFullRights(String email) {
        User targetUser = this.userRepository.findUserByEmail(email).orElseThrow(() -> new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND"));

        return targetUser.isEnabled() && targetUser.isAccountNonLocked();
    }
}
