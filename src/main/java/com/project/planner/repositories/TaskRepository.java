package com.project.planner.repositories;

import com.project.planner.models.Project;
import com.project.planner.models.Task;
import com.project.planner.models.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Set<Task> findAllByTitle(String title);
    List<Task> findAllByCreatedDateBefore(LocalDate localDate);
    List<Task> findAllByCreatedDateAfter(LocalDate localDate);
    List<Task> findAllByTaskStatus(TaskStatus taskStatus);
    Set<Task> findTasksByOriginProject(Project project);
}
