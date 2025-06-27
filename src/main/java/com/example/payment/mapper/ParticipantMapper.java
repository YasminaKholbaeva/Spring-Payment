package com.example.payment.mapper;

import com.example.payment.dto.ParticipantDto;
import com.example.payment.model.Participant;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "event.id", target = "eventId")
    ParticipantDto toDto(Participant participant);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "event", ignore = true)
    Participant toEntity(ParticipantDto participantDto, @Context ParticipantMappingContext context);

    @AfterMapping
    default void fillRelations(
            ParticipantDto participantDto,
            @MappingTarget Participant entity,
            @Context ParticipantMappingContext context
    ) {
        entity.setUser(context.getUserById(participantDto.getUserId()));
        entity.setEvent(context.getEventById(participantDto.getEventId()));
    }
}
