package com.example.payment.conroller;

import com.example.payment.dto.PaymentResultDto;
import com.example.payment.model.Participant;
import com.example.payment.repository.ParticipantRepository;
import com.example.payment.service.impl.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl paymentService;
    private final ParticipantRepository participantRepository;


    @GetMapping("/{id}/total")
    public ResponseEntity<Map<String, Object>> calculateEventTotal(@PathVariable("id") Integer eventId) {
        List<Participant> participants = participantRepository.findByEventId(eventId);
        int participantCount = participants.size();

        BigDecimal totalSpent = participants.stream()
                .map(p -> p.getAmountPaid() != null ? p.getAmountPaid() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal averageSpent = participantCount == 0
                ? BigDecimal.ZERO
                : totalSpent.divide(BigDecimal.valueOf(participantCount), 2, RoundingMode.HALF_UP);

        List<PaymentResultDto> payments = paymentService.calculate(eventId);

        List<Map<String, Object>> calculate = payments.stream().map(p -> {
            Map<String, Object> map = new HashMap<>();
            map.put("participant_paying", p.getParticipantPaying().getName());
            map.put("amount_to_pay", p.getAmount());
            map.put("recipient", p.getRecipient().getName());
            return map;
        }).collect(Collectors.toList());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("event_id", eventId);
        response.put("total_spent", totalSpent);
        response.put("average_spent", averageSpent);
        response.put("calculate", calculate);

        return ResponseEntity.ok(response);
    }



}
