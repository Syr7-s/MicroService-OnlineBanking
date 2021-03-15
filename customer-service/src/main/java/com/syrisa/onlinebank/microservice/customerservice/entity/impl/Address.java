package com.syrisa.onlinebank.microservice.customerservice.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.syrisa.onlinebank.microservice.customerservice.entity.Model;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String country;
    private String city;
    private String district;
    private String street;
    private int zipCode;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Customer customer;
}
