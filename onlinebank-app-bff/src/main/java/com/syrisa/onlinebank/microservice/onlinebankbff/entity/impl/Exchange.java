package com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exchange {
    private UUID processNumber;
    private String fromAccountIban;
    private String toAccountIban;
    private int depositMoney;
    private int receiveMoney;
    private String processType;
    private LocalTime time;
    private LocalDate date;
}
