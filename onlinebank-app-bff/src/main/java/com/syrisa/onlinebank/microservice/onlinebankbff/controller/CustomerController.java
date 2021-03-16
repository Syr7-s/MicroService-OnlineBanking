package com.syrisa.onlinebank.microservice.onlinebankbff.controller;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.CustomerDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.Customer;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {
    private final CustomerService<Customer> customerService;

    public CustomerController(CustomerService<Customer> customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/{customerTC}")
    public CustomerDto get(@PathVariable("customerTC") long customerTC) {
        try {
            return customerService.get(customerTC).toCustomerDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
