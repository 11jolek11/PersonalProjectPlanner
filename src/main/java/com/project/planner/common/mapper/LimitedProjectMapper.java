package com.project.planner.common.mapper;

import com.project.planner.common.dto.LimitedProjectDTO;
import com.project.planner.common.dto.ProjectDTO;
import com.project.planner.models.Project;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.stereotype.Component;

@Component
public class LimitedProjectMapper implements Mapper<Project, LimitedProjectDTO> {
    ModelMapper mapper;

    public LimitedProjectMapper(ModelMapper mapper) {
        this.mapper = mapper;
        this.mapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public LimitedProjectDTO mapTo(Project project) {
        return this.mapper.map(project, LimitedProjectDTO.class);
    }

    @Override
    public Project mapFrom(LimitedProjectDTO createLimitedProjectRequest) {
        return this.mapper.map(createLimitedProjectRequest, Project.class);
    }
}
