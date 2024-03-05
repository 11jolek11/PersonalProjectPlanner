package com.project.planner.common.mapper;

import com.project.planner.common.requests.CreateFullTaskRequest;
import com.project.planner.models.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements Mapper<Task, CreateFullTaskRequest>{

    ModelMapper mapper;

    public TaskMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public CreateFullTaskRequest mapTo(Task task) {
        return this.mapper.map(task, CreateFullTaskRequest.class);
    }

    @Override
    public Task mapFrom(CreateFullTaskRequest createFullTaskRequest) {
        return this.mapper.map(createFullTaskRequest, Task.class);
    }
}
