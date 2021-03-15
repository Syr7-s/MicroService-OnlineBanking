package com.syrisa.onlinebank.microservice.accountservice.entity.impl;

import com.syrisa.onlinebank.microservice.accountservice.dto.ExtractOfAccountDto;
import com.syrisa.onlinebank.microservice.accountservice.entity.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class ExtractOfAccount implements Entity {
    @Id
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
