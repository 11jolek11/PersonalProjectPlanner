package com.project.planner.common.mapper;

import com.project.planner.common.dto.UserDTO;
import com.project.planner.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDTO>{

    ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
//        this.mapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public UserDTO mapTo(User user) {
        return this.mapper.map(user, UserDTO.class);
    }

    @Override
    public User mapFrom(UserDTO createFullUserRequest) {
        return this.mapper.map(createFullUserRequest, User.class);
    }
}
