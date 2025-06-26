package com.example.payment.service;

import com.example.payment.dto.ParticipantDto;
import com.example.payment.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


public interface ParticipantService {
    ParticipantDto addParticipant(ParticipantDto participantDto);
    ParticipantDto updateAmount(Integer eventId, Integer participantId, BigDecimal amount);
    void deleteParticipant(Integer eventId, Integer participantId);



}
