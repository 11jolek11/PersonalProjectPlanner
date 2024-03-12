package com.project.planner.repositories;

import com.project.planner.models.Project;
import com.project.planner.models.Task;
import com.project.planner.models.TaskStatus;
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

    String query = "SELECT task.created_date, task.deadline, task.id, task.origin_project, task.description, task.task_status, task.title, task.notes"
            + " FROM"
            + " project JOIN task ON task.origin_project = project.id"
            + " JOIN _user ON project.maintainer_id = _user.id"
            + " WHERE _user.id=?1";
    @Query(value = query, nativeQuery=true)
    Set<Task> findTasksByMaintainerId(Long maintainerId);

}
