package com.project.planner.common.mapper;

import com.project.planner.common.dto.ProjectDTO;
import com.project.planner.common.dto.TaskDTO;
import com.project.planner.models.Project;
import com.project.planner.models.Task;
import com.project.planner.models.User;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class TaskMapper implements Mapper<Task, TaskDTO>{
    ModelMapper mapper;

    public TaskMapper(ModelMapper mapper) {
        this.mapper = mapper;
//        this.mapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public TaskDTO mapTo(Task task) {
        return this.mapper.map(task, TaskDTO.class);
    }

    @Override
    public Task mapFrom(TaskDTO createFullTaskRequest) {
        return this.mapper.map(createFullTaskRequest, Task.class);
    }

    public Task update(TaskDTO dto, Task task) {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        this.mapper.map(dto, task);
        return task;
    }
}
