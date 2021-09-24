package com.hoanghiep.reactiveprojectdemo.mapper;

import com.hoanghiep.reactiveprojectdemo.entity.User;
import com.hoanghiep.reactiveprojectdemo.model.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
