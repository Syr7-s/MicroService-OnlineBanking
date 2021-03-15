package com.syrisa.onlinebank.microservice.customerservice.dto;

import com.syrisa.onlinebank.microservice.customerservice.entity.impl.Address;
import com.syrisa.onlinebank.microservice.customerservice.entity.impl.Customer;
import com.syrisa.onlinebank.microservice.customerservice.utility.enums.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CustomerDto {
    private long customerTC;
    private String customerName;
    private String customerLastname;
    private Gender customerGender;
    private String customerPhone;
    private String customerEmail;
    private LocalDate customerBirthDate;
    private boolean isState;
    private Address address;

    public Customer toCustomer() {
        return Customer.builder()
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
