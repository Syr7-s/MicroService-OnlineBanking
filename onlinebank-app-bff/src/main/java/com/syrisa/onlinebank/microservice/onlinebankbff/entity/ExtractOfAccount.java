package com.syrisa.onlinebank.microservice.onlinebankbff.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtractOfAccount {
    private UUID id;
    private long accountNumber;
    private int money;
    private LocalDate date;
    private LocalTime time;
}
