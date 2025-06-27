package com.example.payment.service.impl;

import com.example.payment.dto.ParticipantDto;
import com.example.payment.mapper.ParticipantMapper;
import com.example.payment.mapper.ParticipantMappingContext;
import com.example.payment.model.Participant;
import com.example.payment.repository.ParticipantRepository;
import com.example.payment.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;
    private final ParticipantMappingContext participantMappingContext;

    @Override
    public ParticipantDto addParticipant(ParticipantDto participantDto) {
        Participant participant = participantMapper.toEntity(participantDto, participantMappingContext);
        participant.setCreatedAt(LocalDateTime.now());
        participant.setUpdatedAt(LocalDateTime.now());
        return participantMapper.toDto(participantRepository.save(participant));
    }

    @Override
    public ParticipantDto updateAmount(Integer eventId, Integer participantId, BigDecimal amount) {
        Participant p = participantRepository.findByIdAndEventId(participantId, eventId).orElseThrow();
        p.setAmountPaid(amount);
        p.setUpdatedAt(LocalDateTime.now());
        return participantMapper.toDto(participantRepository.save(p));

    }

    @Override
    public void deleteParticipant(Integer eventId, Integer participantId) {
        participantRepository.deleteByIdAndEventId(participantId, eventId);
    }


    public List<ParticipantDto> getAllParticipants(Integer eventId) {
        return participantRepository.findAll().stream().map(participantMapper::toDto).toList();
    }


}
