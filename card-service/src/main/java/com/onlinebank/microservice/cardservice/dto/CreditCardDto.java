package com.onlinebank.microservice.cardservice.dto;


import com.onlinebank.microservice.cardservice.entity.impl.CreditCard;
import com.onlinebank.microservice.cardservice.utility.currency.Currency;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDate;

@Data
@Builder
public class CreditCardDto {
    private long cardAccountNumber;
    private String cardNameSurname;
    private int cardPassword;
    private LocalDate expirationDate;
    private String securityCode;
    private int cardLimit;
    private int cardDebt;
    private Currency currency;
    private long userID;
    public CreditCard toCreditCard(){
        return CreditCard.builder()
                .cardAccountNumber(this.cardAccountNumber)
                .cardNameSurname(this.cardNameSurname)
                .cardPassword(this.cardPassword)
                .expirationDate(this.expirationDate)
                .securityCode(this.securityCode)
                .cardLimit(this.cardLimit)
                .cardDebt(this.cardDebt)
                .currency(this.currency)
                .userID(this.userID)
                .build();
    }
}
