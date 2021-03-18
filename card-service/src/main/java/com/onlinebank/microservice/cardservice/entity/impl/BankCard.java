package com.onlinebank.microservice.cardservice.entity.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onlinebank.microservice.cardservice.dto.BankCardDto;
import com.onlinebank.microservice.cardservice.entity.Card;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BankCard implements Card {
    @Id
    private long bankCardAccountNumber;
    private String bankCardNameSurname;
    private int bankCardPassword;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bankCardExpirationDate;
    private String bankCardSecurityCode;

    public BankCardDto toBankCardDto(){
        return BankCardDto.builder()
                .bankCardAccountNumber(this.bankCardAccountNumber)
                .bankCardNameSurname(this.bankCardNameSurname)
                .bankCardPassword(this.bankCardPassword)
                .bankCardExpirationDate(this.bankCardExpirationDate)
                .bankCardSecurityCode(this.bankCardSecurityCode)
                .build();
    }
}
