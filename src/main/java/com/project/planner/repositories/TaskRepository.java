package com.project.planner.repositories;

import com.project.planner.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public class TaskRepository implements JpaRepository<Task, long int> {
}
