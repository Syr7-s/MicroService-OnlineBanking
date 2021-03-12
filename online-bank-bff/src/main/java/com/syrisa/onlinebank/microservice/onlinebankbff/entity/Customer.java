package com.syrisa.onlinebank.microservice.onlinebankbff.entity;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.CustomerDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.utility.enums.gender.Gender;
import lombok.*;

import java.time.LocalDate;

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
                .build();
    }
}
