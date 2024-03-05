package com.project.planner.common.mapper;

import com.project.planner.common.requests.CreateFullProjectRequest;
import com.project.planner.models.Project;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements Mapper<Project, CreateFullProjectRequest> {
    ModelMapper mapper;

    public ProjectMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public CreateFullProjectRequest mapTo(Project project) {
        return this.mapper.map(project, CreateFullProjectRequest.class);
    }

    @Override
    public Project mapFrom(CreateFullProjectRequest createFullProjectRequest) {
        return this.mapper.map(createFullProjectRequest, Project.class);
    }
}
