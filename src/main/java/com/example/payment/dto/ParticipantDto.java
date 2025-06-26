package com.example.payment.dto;

import com.example.payment.model.Event;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class ParticipantDto {
    private Integer id;
    private Integer participantId;
    private BigDecimal amountPaid;
    private Integer eventId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
