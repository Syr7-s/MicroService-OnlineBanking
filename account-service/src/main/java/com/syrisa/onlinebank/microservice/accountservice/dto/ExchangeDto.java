package com.syrisa.onlinebank.microservice.accountservice.dto;

import com.syrisa.onlinebank.microservice.accountservice.entity.impl.Exchange;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class ExchangeDto {
    private long fromAccountIban;
    private long toAccountIban;
    private int depositMoney;
    private int receiveMoney;
    private String processType;
    private LocalTime time;
    private LocalDate date;

    public Exchange toExchange(){
        return Exchange.builder()
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
