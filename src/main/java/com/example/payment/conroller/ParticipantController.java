package com.example.payment.conroller;


import com.example.payment.dto.ParticipantDto;
import com.example.payment.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class ParticipantController {

   private final ParticipantService participantService;

    @PostMapping("_{eventId}/attendee")
    public ResponseEntity<ParticipantDto> addParticipant(@RequestBody ParticipantDto participantDto) {
        return ResponseEntity.ok(participantService.addParticipant(participantDto));
    }

    @PutMapping("_{eventId}/attendee")
    public ResponseEntity<ParticipantDto> updateContribution(@RequestBody ParticipantDto participantDto) {
        return ResponseEntity.ok(participantService.updateAmount(participantDto.getEventId(), participantDto.getParticipantId(), participantDto.getAmountPaid()));
    }

    @DeleteMapping("_{eventId}/attendee")
    public ResponseEntity<Map<String, Object>> deleteParticipant(@RequestBody ParticipantDto participantDto) {
        participantService.deleteParticipant(participantDto.getEventId(), participantDto.getParticipantId());
        return ResponseEntity.ok(Map.of("result", 0, "comment", "Участник был удалён."));
    }


    //todo CRUD


}
