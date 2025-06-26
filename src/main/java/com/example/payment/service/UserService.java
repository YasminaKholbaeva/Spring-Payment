package com.example.payment.service;

import com.example.payment.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(Integer id, UserDto userDto);

    void deleteUser(Integer id);


}
