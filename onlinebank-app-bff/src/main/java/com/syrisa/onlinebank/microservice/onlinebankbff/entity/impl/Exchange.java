package com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.ExchangeDto;
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

    public ExchangeDto toExchangeDto() {
        return ExchangeDto.builder()
                .processNumber(this.processNumber)
                .fromAccountIban(this.fromAccountIban)
                .toAccountIban(this.toAccountIban)
                .depositMoney(this.depositMoney)
                .receiveMoney(this.receiveMoney)
                .processType(this.processType)
                .time(this.time)
                .date(this.date)
                .build();
    }
}
