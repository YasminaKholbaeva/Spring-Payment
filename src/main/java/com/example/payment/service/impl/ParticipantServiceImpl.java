package com.example.payment.service.impl;

import com.example.payment.dto.ParticipantDto;
import com.example.payment.mapper.ParticipantMapper;
import com.example.payment.mapper.ParticipantMapperHelper;
import com.example.payment.model.Participant;
import com.example.payment.repository.EventRepository;
import com.example.payment.repository.ParticipantRepository;
import com.example.payment.repository.UserRepository;
import com.example.payment.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;
    private final ParticipantMapperHelper participantMapperHelper;

    @Override
    public ParticipantDto addParticipant(ParticipantDto participantDto) {
        Participant p = participantMapper.toParticipant(participantDto, participantMapperHelper);
        p.setCreatedAt(LocalDateTime.now());
        p.setUpdatedAt(LocalDateTime.now());
        return participantMapper.toParticipantDto(participantRepository.save(p));
    }

    @Override
    public ParticipantDto updateAmount(Integer eventId, Integer participantId, BigDecimal amount) {
        Participant p = participantRepository.findByEventIdAndParticipantId(eventId, participantId).orElseThrow();
        p.setAmountPaid(amount);
        p.setUpdatedAt(LocalDateTime.now());
        return participantMapper.toParticipantDto(participantRepository.save(p));

    }

    @Override
    public void deleteParticipant(Integer eventId, Integer participantId) {
        participantRepository.deleteByEventIdAndParticipantId(eventId, participantId);
    }
}
