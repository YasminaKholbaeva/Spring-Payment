package com.example.payment.conroller;


import com.example.payment.dto.EventDto;
import com.example.payment.dto.ParticipantDto;
import com.example.payment.model.Participant;
import com.example.payment.service.ParticipantService;
import com.example.payment.service.impl.ParticipantServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantServiceImpl participantService;

    @PostMapping
    public ResponseEntity<ParticipantDto> addParticipant(@RequestBody ParticipantDto participantDto) {
        System.out.println("OK");
        return ResponseEntity.ok(participantService.addParticipant(participantDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ParticipantDto> updateContribution(@RequestBody ParticipantDto participantDto) {
        return ResponseEntity.ok(participantService.updateAmount(participantDto.getEventId(), participantDto.getUserId(), participantDto.getAmountPaid()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Object>> deleteParticipant(@RequestBody ParticipantDto participantDto) {
        participantService.deleteParticipant(participantDto.getEventId(), participantDto.getUserId());
        return ResponseEntity.ok(Map.of("result", 0, "comment", "Участник был удалён."));
    }

    @GetMapping
    public ResponseEntity<List<ParticipantDto>> getAllParticipants(@PathVariable("id") Integer eventId) {
        return ResponseEntity.ok(participantService.getAllParticipants(eventId));
    }


}
