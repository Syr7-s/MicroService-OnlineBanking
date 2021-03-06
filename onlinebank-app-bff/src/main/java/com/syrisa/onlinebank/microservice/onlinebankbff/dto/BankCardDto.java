package com.syrisa.onlinebank.microservice.onlinebankbff.dto;

import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.BankCard;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BankCardDto {
    private long bankCardAccountNumber;
    private String bankCardNameSurname;
    private int bankCardPassword;
    private LocalDate bankCardExpirationDate;
    private String bankCardSecurityCode;

    public BankCard toBankCard(){
        return BankCard.builder()
                .bankCardAccountNumber(this.bankCardAccountNumber)
                .bankCardNameSurname(this.bankCardNameSurname)
                .bankCardPassword(this.bankCardPassword)
                .bankCardExpirationDate(this.bankCardExpirationDate)
                .bankCardSecurityCode(this.bankCardSecurityCode)
                .build();
    }
}
