package com.project.planner.common.mapper;

import com.project.planner.common.dto.TaskDTO;
import com.project.planner.models.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
}
