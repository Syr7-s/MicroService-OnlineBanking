package com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl;

import com.syrisa.onlinebank.microservice.onlinebankbff.entity.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address implements Entity {
    private int id;
    private String country;
    private String city;
    private String district;
    private String street;
    private int zipCode;
}
