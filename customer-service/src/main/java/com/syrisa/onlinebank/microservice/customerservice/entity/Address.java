package com.syrisa.onlinebank.microservice.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
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
