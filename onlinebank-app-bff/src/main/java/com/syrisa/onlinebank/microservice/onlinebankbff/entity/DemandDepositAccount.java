package com.syrisa.onlinebank.microservice.onlinebankbff.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.syrisa.onlinebank.microservice.onlinebankbff.utility.enums.currency.Currency;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandDepositAccount {
    private long accountNumber;
    private String accountIban;
    private int accountBalance;
    private Currency accountCurrency;
    private LocalDate accountCreationDate;
    private long customerTC;
}
