package com.example.payment.service.impl;

import com.example.payment.dto.EventDto;
import com.example.payment.mapper.EventMapper;
import com.example.payment.model.Event;
import com.example.payment.repository.EventRepository;
import com.example.payment.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;


    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event event = eventMapper.toEvent(eventDto);
        return eventMapper.toEventDto(eventRepository.save(event));
    }

    @Override
    public EventDto updateEvent(Integer id, EventDto eventDto) {
        Event event = eventRepository.findById(id).orElseThrow();
        event.setEvent(eventDto.getEvent());
        return eventMapper.toEventDto(eventRepository.save(event));
    }

    @Override
    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<EventDto> getAllEvents() {
        return eventRepository.findAll().stream().map(eventMapper::toEventDto).toList();
    }




}
