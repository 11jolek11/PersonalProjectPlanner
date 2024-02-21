package com.project.planner.services;

import com.project.planner.exceptions.EntityInstanceDoesNotExist;
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
    public Boolean checkIfUserHaveFullRights(Long id) {
        User targetUser = this.userRepository.findById(id).orElseThrow(() -> {
            return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
        });

        return targetUser.isEnabled() && targetUser.isAccountNonLocked();
    }

    public Boolean checkIfUserHaveFullRights(String email) {
        User targetUser = this.userRepository.findUserByEmail(email).orElseThrow(() -> {
            return new EntityInstanceDoesNotExist(HttpStatus.NOT_FOUND, "User NOT FOUND");
        });

        return targetUser.isEnabled() && targetUser.isAccountNonLocked();
    }
}
