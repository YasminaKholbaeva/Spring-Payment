package com.example.payment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
public class UserDto {
    private Integer id;
    private String name;
    private String lastname;
    private String mobileNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
