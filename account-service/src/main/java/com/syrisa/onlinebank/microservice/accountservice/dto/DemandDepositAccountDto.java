package com.syrisa.onlinebank.microservice.accountservice.dto;

import com.syrisa.onlinebank.microservice.accountservice.utility.currency.Currency;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class DemandDepositAccountDto extends AccountDto {
    private long accountNumber;
    private String accountIban;
    private int accountBalance;
    private Currency accountCurrency;
    private LocalDate accountCreationDate;
    private long customerTC;

    public DemandDepositAccountDto toDemandDepositAccountDto() {
        return (DemandDepositAccountDto) AccountDto.builder()
                .accountNumber(this.accountNumber)
                .accountIban(this.accountIban)
                .accountBalance(this.accountBalance)
                .accountCurrency(this.accountCurrency)
                .accountCreationDate(this.accountCreationDate)
                .customerTC(this.customerTC)
                .build();
    }
}
