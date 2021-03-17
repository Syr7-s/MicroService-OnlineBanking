package com.syrisa.onlinebank.microservice.onlinebankbff.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
public class ExchangeDto {
    private UUID processNumber;
    private String fromAccountIban;
    private String toAccountIban;
    private int depositMoney;
    private int receiveMoney;
    private String processType;
    private LocalTime time;
    private LocalDate date;

}
