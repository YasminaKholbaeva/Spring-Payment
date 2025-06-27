package com.example.payment.mapper;


import com.example.payment.dto.UserDto;
import com.example.payment.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto userDto);
}
