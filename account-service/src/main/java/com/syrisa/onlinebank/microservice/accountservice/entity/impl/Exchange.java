package com.syrisa.onlinebank.microservice.accountservice.entity.impl;

import com.syrisa.onlinebank.microservice.accountservice.dto.ExchangeDto;
import com.syrisa.onlinebank.microservice.accountservice.entity.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Exchange implements Entity {
    @Id
    private UUID processNumber;
    private String fromAccountIban;
    private String toAccountIban;
    private int depositMoney;
    private int receiveMoney;
    private String processType;
    private LocalTime time;
    private LocalDate date;

    public ExchangeDto toExchangeDto(){
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
