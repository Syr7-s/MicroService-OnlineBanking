package com.syrisa.onlinebank.microservice.onlinebankbff.dto;

import com.syrisa.onlinebank.microservice.onlinebankbff.entity.ExtractOfAccount;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
public class ExtractOfAccountDto {
    private UUID id;
    private long accountNumber;
    private int money;
    private LocalDate date;
    private LocalTime time;

    public ExtractOfAccount toExtractOfAccount(){
        return ExtractOfAccount.builder()
                .id(this.id)
                .accountNumber(this.accountNumber)
                .money(this.money)
                .date(this.date)
                .time(this.time)
                .build();
    }
}
