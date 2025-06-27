package com.example.payment.dto;

import com.example.payment.model.Event;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class ParticipantDto {
    private Integer id;
    private Integer userId;
    private BigDecimal amountPaid;
    private Integer eventId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
