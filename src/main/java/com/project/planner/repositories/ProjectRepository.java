package com.project.planner.repositories;

import com.project.planner.models.Project;
import com.project.planner.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    Optional<Project> findProjectByMaintainer(User maintainer);
    Set<Project> findProjectsByTitle(String title);
}
