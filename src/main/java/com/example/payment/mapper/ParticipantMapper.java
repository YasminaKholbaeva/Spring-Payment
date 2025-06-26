package com.example.payment.mapper;

import com.example.payment.dto.ParticipantDto;
import com.example.payment.model.Participant;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    @Mapping(source = "participant.id", target = "participantId")
    @Mapping(source = "participant.name", target = "name")
    @Mapping(source = "participant.lastname", target = "lastname")
    @Mapping(source = "event.id", target = "eventId")
    ParticipantDto toParticipantDto(Participant participant);

    @Mapping(target = "participant", expression = "java(mapperHelper.findUserById(dto.getParticipantId()))")
    @Mapping(target = "event", expression = "java(mapperHelper.findEventById(dto.getEventId()))")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Participant toParticipant(ParticipantDto dto, @Context ParticipantMapperHelper mapperHelper);
}
