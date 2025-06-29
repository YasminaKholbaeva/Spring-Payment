package com.example.payment.mapper;

import com.example.payment.model.Event;
import com.example.payment.model.User;
import com.example.payment.repository.EventRepository;
import com.example.payment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipantMappingContext {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public User getUserById(Integer id) {
        if (id == null) throw new IllegalArgumentException("User ID must not be null");
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    public Event getEventById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found: " + id));
    }
}
