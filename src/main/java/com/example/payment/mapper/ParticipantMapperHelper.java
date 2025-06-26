package com.example.payment.mapper;

import com.example.payment.model.Event;
import com.example.payment.model.User;
import com.example.payment.repository.EventRepository;
import com.example.payment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipantMapperHelper {
   private final UserRepository userRepository;
   private final EventRepository eventRepository;

    public User findUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    public Event findEventById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found: " + id));
    }
}
