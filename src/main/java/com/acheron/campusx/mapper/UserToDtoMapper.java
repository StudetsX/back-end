package com.acheron.campusx.mapper;

import com.acheron.campusx.security.dto.UserResponseDto;
import com.acheron.campusx.security.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoMapper {
    public UserResponseDto map(User user){
        return new UserResponseDto(user.getFirstName(), user.getLastName(), user.getImage(),user.getGroup().getName());
    }
}
