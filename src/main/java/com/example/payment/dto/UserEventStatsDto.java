package com.example.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UserEventStatsDto {
    private Integer eventId;
    private String eventName;
    private BigDecimal userPaid;
    private BigDecimal equalShare;
    private BigDecimal difference;
}
