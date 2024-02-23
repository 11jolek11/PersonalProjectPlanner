package com.project.planner.repositories;

import com.project.planner.models.Project;
import com.project.planner.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Boolean existsByEmail(String email);
}
