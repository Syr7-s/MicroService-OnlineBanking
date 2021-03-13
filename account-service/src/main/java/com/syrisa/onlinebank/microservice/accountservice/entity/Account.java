package com.syrisa.onlinebank.microservice.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.syrisa.onlinebank.microservice.accountservice.utility.currency.Currency;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Account {
    @Id
    private long accountNumber;
    private String accountIban;
    private int accountBalance;
    private Currency accountCurrency;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate accountCreationDate;
    private long customerTC;

}
