package com.example.payment.service.impl;

import com.example.payment.dto.UserDto;
import com.example.payment.mapper.UserMapper;
import com.example.payment.model.User;
import com.example.payment.repository.UserRepository;
import com.example.payment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByMobileNumber(userDto.getMobileNumber())) {
            throw new RuntimeException("Mobile number already registered");
        }
        User user = new User();
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userMapper.toUserDto(userRepository.save(user));
    }

    private UserDto userDto(User user) {
        return userMapper.toUserDto(user);
    }

    public UserDto updateUser(Integer id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow();
        if (userDto.getName() != null) user.setName(userDto.getName());
        if (userDto.getLastname() != null) user.setLastname(userDto.getLastname());
        if (userDto.getMobileNumber() != null) user.setMobileNumber(userDto.getMobileNumber());
        user.setUpdatedAt(LocalDateTime.now());
        return userMapper.toUserDto(userRepository.save(user));
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    //Добавление нового пользователя.
    //
    //Обновление.
    //
    //Удаление (с удалением всех связей).
    //
    //Получение статистики по пользователю.

}
