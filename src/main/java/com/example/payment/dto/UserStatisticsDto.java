package com.example.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStatisticsDto {
    private Integer userId;
    private String name;
    private BigDecimal balance;
    private List<UserEventStatsDto> events;
}
