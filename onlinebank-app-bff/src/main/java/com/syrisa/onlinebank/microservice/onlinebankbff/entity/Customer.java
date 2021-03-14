package com.syrisa.onlinebank.microservice.onlinebankbff.entity;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.CustomerDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.utility.enums.gender.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    private long customerTC;
    private String customerName;
    private String customerLastname;
    private Gender customerGender;
    private String customerPhone;
    private String customerEmail;
    private LocalDate customerBirthDate;
    private boolean isState;
    private Address address;
    private List<DemandDepositAccount> demandDepositAccounts;
    private List<SavingsAccount> savingsAccounts;

    public CustomerDto toCustomerDto() {
        return CustomerDto.builder()
                .customerTC(this.customerTC)
                .customerName(this.customerName)
                .customerLastname(this.customerLastname)
                .customerGender(this.customerGender)
                .customerPhone(this.customerPhone)
                .customerEmail(this.customerEmail)
                .customerBirthDate(this.customerBirthDate)
                .isState(this.isState)
                .address(this.address)
                .demandDepositAccounts(this.demandDepositAccounts)
                .savingsAccounts(this.savingsAccounts)
                .build();
    }
}
