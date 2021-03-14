package com.syrisa.onlinebank.microservice.onlinebankbff.entity;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.SavingsAccountDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.utility.enums.currency.Currency;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsAccount {
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

    public SavingsAccountDto toSavingsAccountDto() {
        return SavingsAccountDto.builder()
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
