package com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.ExtractOfAccountDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtractOfAccount implements Entity {
    private UUID id;
    private long accountNumber;
    private int money;
    private LocalDate date;
    private LocalTime time;

    public ExtractOfAccountDto toExtractOfAccountDto() {
        return ExtractOfAccountDto.builder()
                .id(this.id)
                .accountNumber(this.accountNumber)
                .money(this.money)
                .date(this.date)
                .time(this.time)
                .build();
    }
}
