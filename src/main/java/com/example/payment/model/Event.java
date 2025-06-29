package com.example.payment.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 32)
    private String event;



}
