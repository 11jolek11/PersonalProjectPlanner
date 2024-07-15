package com.project.planner.repositories;

import com.project.planner.models.Project;
import com.project.planner.models.Task;
import com.project.planner.models.TaskStatus;
import com.project.planner.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Set<Task> findAllByTitle(String title);
//    Set<Task> findAllByOriginProject(Project originProject);
    List<Task> findAllByCreatedDateBefore(LocalDate createdDate);
    List<Task> findAllByCreatedDateAfter(LocalDate createdDate);
    List<Task> findAllByTaskStatus(TaskStatus taskStatus);
    Set<Task> findTasksByOriginProject(Project originProject);
    Set<Task> findTasksByOwner(User owner);
}
