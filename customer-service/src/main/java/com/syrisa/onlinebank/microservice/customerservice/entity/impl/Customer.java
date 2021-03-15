package com.syrisa.onlinebank.microservice.customerservice.entity.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.syrisa.onlinebank.microservice.customerservice.dto.CustomerDto;
import com.syrisa.onlinebank.microservice.customerservice.entity.Model;
import com.syrisa.onlinebank.microservice.customerservice.utility.enums.Gender;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer implements Model {
    @Id
    private long customerTC;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "customerName is invalid")
    private String customerName;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "customerLastName is invalid")
    private String customerLastname;
    @Enumerated(EnumType.STRING)
    private Gender customerGender;
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "Phone Number Ex:+(123)-456-78-90")
    @Length(min = 18, max = 18)
    private String customerPhone;
    @Pattern(regexp = "^(.+)@(.+)$", message = "email is invalid.")
    private String customerEmail;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate customerBirthDate;
    private boolean isState;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    @JsonIgnore
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
