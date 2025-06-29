package com.example.payment.service;

import com.example.payment.dto.EventDto;

import java.util.List;

public interface EventService {
    EventDto createEvent(EventDto eventDto);

    EventDto updateEvent(Integer id, EventDto eventDto);

    void deleteEvent(Integer id);

    List<EventDto> getAllEvents();
}
