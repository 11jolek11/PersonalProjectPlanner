package com.project.planner.common.mapper;

import com.project.planner.common.dto.ProjectDTO;
import com.project.planner.models.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements Mapper<Project, ProjectDTO> {
    ModelMapper mapper;

    public ProjectMapper(ModelMapper mapper) {
        this.mapper = mapper;
//        this.mapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public ProjectDTO mapTo(Project project) {
        return this.mapper.map(project, ProjectDTO.class);
    }

    @Override
    public Project mapFrom(ProjectDTO createFullProjectRequest) {
        return this.mapper.map(createFullProjectRequest, Project.class);
    }
}
