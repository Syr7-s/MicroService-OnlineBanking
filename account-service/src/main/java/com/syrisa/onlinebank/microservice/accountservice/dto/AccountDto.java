package com.syrisa.onlinebank.microservice.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.syrisa.onlinebank.microservice.accountservice.entity.Account;
import com.syrisa.onlinebank.microservice.accountservice.utility.currency.Currency;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AccountDto {
    private long accountNumber;
    private String accountIban;
    private int accountBalance;
    private Currency accountCurrency;
    private LocalDate accountCreationDate;
    private long customerTC;

    public Account toAccount() {
        return Account.builder()
                .accountNumber(this.accountNumber)
                .accountIban(this.accountIban)
                .accountBalance(this.accountBalance)
                .accountCurrency(this.accountCurrency)
                .accountCreationDate(this.accountCreationDate)
                .customerTC(this.customerTC)
                .build();
    }
}
