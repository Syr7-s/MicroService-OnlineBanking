package com.syrisa.onlinebank.microservice.onlinebankbff.dto;

import com.syrisa.onlinebank.microservice.onlinebankbff.entity.SavingsAccount;
import com.syrisa.onlinebank.microservice.onlinebankbff.utility.enums.currency.Currency;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SavingsAccountDto{
    private long accountNumber;
    private String accountIban;
    private int accountBalance;
    private Currency accountCurrency;
    private LocalDate accountCreationDate;
    private long customerTC;
    private int termTime;
    private double grossInterestReturn;
    private double savingsAccountNetGain;
    private double savingsAccountInterestRate;

    public SavingsAccount toSavingsAccount(){
        return SavingsAccount.builder()
                .accountNumber(this.accountNumber)
                .accountIban(this.accountIban)
                .accountBalance(this.accountBalance)
                .accountCurrency(this.accountCurrency)
                .accountCreationDate(this.accountCreationDate)
                .customerTC(this.customerTC)
                .termTime(this.termTime)
                .grossInterestReturn(this.grossInterestReturn)
                .savingsAccountNetGain(this.savingsAccountNetGain)
                .savingsAccountInterestRate(this.savingsAccountInterestRate)
                .build();
    }
}
