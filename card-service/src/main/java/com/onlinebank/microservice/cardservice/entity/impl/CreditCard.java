package com.onlinebank.microservice.cardservice.entity.impl;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.onlinebank.microservice.cardservice.dto.CreditCardDto;
import com.onlinebank.microservice.cardservice.entity.Card;
import com.onlinebank.microservice.cardservice.utility.currency.Currency;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreditCard implements Card {
    @Id
    private long cardAccountNumber;
    private String cardNameSurname;
    private int cardPassword;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    private String securityCode;
    private int cardLimit;
    private int cardDebt;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    public CreditCardDto toCreditCardDto(){
        return CreditCardDto.builder()
                .cardAccountNumber(this.cardAccountNumber)
                .cardNameSurname(this.cardNameSurname)
                .cardPassword(this.cardPassword)
                .expirationDate(this.expirationDate)
                .securityCode(this.securityCode)
                .cardLimit(this.cardLimit)
                .cardDebt(this.cardDebt)
                .currency(this.currency)
                .build();
    }


}
