package com.project.planner.common.mapper;

import com.project.planner.common.dto.ProjectDTO;
import com.project.planner.common.dto.TaskDTO;
import com.project.planner.models.Project;
import com.project.planner.models.Task;
import com.project.planner.models.User;
import org.modelmapper.Conditions;
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
    public Project mapFrom(ProjectDTO projectDTO) {
        return this.mapper.map(projectDTO, Project.class);
    }

    public Project update(ProjectDTO dto, Project project) {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        this.mapper.map(dto, project);
        return project;
    }
}
