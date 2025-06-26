package com.example.payment.conroller;

import com.example.payment.dto.PaymentResultDto;
import com.example.payment.model.Participant;
import com.example.payment.service.impl.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl paymentService;


    @GetMapping("_{eventId}/total")
    public ResponseEntity<Map<String, Object>> calculateEventTotal(@PathVariable Integer eventId, @RequestParam int page, @RequestParam int size) {
        List<PaymentResultDto> payments = paymentService.calculate(eventId);

        BigDecimal totalSpent = payments.stream()
                .map(PaymentResultDto::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Object> response = new HashMap<>();
        response.put("event_id", eventId);
        response.put("total_spent", totalSpent);
        response.put("average_spent", payments.isEmpty() ? 0 : totalSpent.divide(BigDecimal.valueOf(payments.size()), 2, RoundingMode.HALF_UP));
        response.put("calculate", payments);

        return ResponseEntity.ok(response);




    }



    //
}
