package com.example.payment.service;

import com.example.payment.dto.PaymentResultDto;
import com.example.payment.model.Participant;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    List<PaymentResultDto> calculate(Integer eventId);


}
