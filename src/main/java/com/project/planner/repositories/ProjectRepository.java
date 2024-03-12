package com.project.planner.repositories;

import com.project.planner.models.Project;
import com.project.planner.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    Set<Project> findProjectByMaintainer(User maintainer);
    Optional<Project> findProjectsByIdAndMaintainer(Long id, User maintainer);
    Boolean existsProjectByIdAndMaintainer(Long id, User maintainer);
    Set<Project> findProjectsByTitle(String title);
}
