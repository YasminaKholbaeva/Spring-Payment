package com.example.payment.service.impl;

import com.example.payment.dto.UserDto;
import com.example.payment.dto.UserEventStatsDto;
import com.example.payment.dto.UserStatisticsDto;
import com.example.payment.mapper.UserMapper;
import com.example.payment.model.Event;
import com.example.payment.model.Participant;
import com.example.payment.model.User;
import com.example.payment.repository.ParticipantRepository;
import com.example.payment.repository.UserRepository;
import com.example.payment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ParticipantRepository participantRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByMobileNumber(userDto.getMobileNumber())) {
            throw new RuntimeException("Mobile number already registered");
        }
        User user = userMapper.toUser(userDto);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userMapper.toUserDto(userRepository.save(user));
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
    public UserStatisticsDto getUserStatistics(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Participant> participations = participantRepository.findByUser(user);
        List<UserEventStatsDto> stats = new ArrayList<>();
        BigDecimal totalBalance = BigDecimal.ZERO;

        for (Participant p : participations) {
            Event event = p.getEvent();
            List<Participant> allParticipants = participantRepository.findByEventId(event.getId());

            BigDecimal totalPaid = allParticipants.stream()
                    .map(part -> part.getAmountPaid() != null ? part.getAmountPaid() : BigDecimal.ZERO)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal share = totalPaid.divide(BigDecimal.valueOf(allParticipants.size()), 2, RoundingMode.HALF_UP);
            BigDecimal paid = p.getAmountPaid() != null ? p.getAmountPaid() : BigDecimal.ZERO;
            BigDecimal difference = paid.subtract(share);
            totalBalance = totalBalance.add(difference);

            stats.add(new UserEventStatsDto(event.getId(), event.getEvent(),
                    paid,
                    share,
                    difference
            ));
        }

        UserStatisticsDto dto = UserStatisticsDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .balance(totalBalance)
                .events(stats).build();

        return dto;
    }

//        if (id == null) {
//            throw new IllegalArgumentException("User ID must not be null");
//        }
//        User users = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found with id: " + id));
//
//        return userMapper.toUserDto(users);
//    }

}
