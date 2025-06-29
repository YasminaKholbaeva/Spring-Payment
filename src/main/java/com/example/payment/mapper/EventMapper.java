package com.example.payment.mapper;

import com.example.payment.dto.EventDto;
import com.example.payment.model.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDto toEventDto(Event event);

    Event toEvent(EventDto dto);
}
