package com.syrisa.onlinebank.microservice.accountservice.entity.impl;

import com.syrisa.onlinebank.microservice.accountservice.dto.ExchangeDto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Exchange {
    @MongoId
    private long fromAccountIban;
    private long toAccountIban;
    private int depositMoney;
    private int receiveMoney;
    private String processType;
    private LocalTime time;
    private LocalDate date;

    public ExchangeDto toExchangeDto(){
        return ExchangeDto.builder()
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
