package com.example.payment.dto;

import com.example.payment.model.User;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResultDto {
    private User participantPaying;
    private User recipient;
    private BigDecimal amount;


}
