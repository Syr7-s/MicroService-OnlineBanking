package com.onlinebank.microservice.cardservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onlinebank.microservice.cardservice.utility.currency.Currency;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
}
