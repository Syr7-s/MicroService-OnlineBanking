package com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl;


import com.syrisa.onlinebank.microservice.onlinebankbff.dto.BankCardDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.Entity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankCard implements Entity {
    private long bankCardAccountNumber;
    private String bankCardNameSurname;
    private int bankCardPassword;
    private LocalDate bankCardExpirationDate;
    private String bankCardSecurityCode;

    public BankCardDto toBankCardDto() {
        return BankCardDto.builder()
                .bankCardAccountNumber(this.bankCardAccountNumber)
                .bankCardNameSurname(this.bankCardNameSurname)
                .bankCardPassword(this.bankCardPassword)
                .bankCardExpirationDate(this.bankCardExpirationDate)
                .bankCardSecurityCode(this.bankCardSecurityCode)
                .build();
    }
}
