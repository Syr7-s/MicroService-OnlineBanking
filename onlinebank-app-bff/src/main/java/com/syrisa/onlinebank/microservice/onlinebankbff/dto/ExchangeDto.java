package com.syrisa.onlinebank.microservice.onlinebankbff.dto;

import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.Exchange;
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

    public Exchange toExchange(){
        return Exchange.builder()
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
