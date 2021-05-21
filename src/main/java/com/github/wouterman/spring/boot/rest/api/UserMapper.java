package com.github.wouterman.spring.boot.rest.api;

import com.github.wouterman.spring.boot.rest.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserDto userToUserDto(User user);

  User userDtoToUser(UserDto userDto);
}
