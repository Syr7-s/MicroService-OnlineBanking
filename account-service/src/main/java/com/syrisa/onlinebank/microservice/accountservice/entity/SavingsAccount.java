package com.syrisa.onlinebank.microservice.accountservice.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class SavingsAccount extends Account{
    private int termTime;
    private double grossInterestReturn;
    private double savingsAccountNetGain;
    private double savingsAccountInterestRate;
}
