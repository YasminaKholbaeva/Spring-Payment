package com.example.payment.dto;

import com.example.payment.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Getter
@Setter
public class PaymentResultDto {
    private User participantPaying;
    private User recipient;
    private BigDecimal amount;


}
