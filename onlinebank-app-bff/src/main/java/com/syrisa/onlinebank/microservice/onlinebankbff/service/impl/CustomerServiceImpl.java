package com.syrisa.onlinebank.microservice.onlinebankbff.service.impl;

import com.syrisa.onlinebank.microservice.onlinebankbff.client.CustomerServiceClient;
import com.syrisa.onlinebank.microservice.onlinebankbff.dto.CustomerDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.Customer;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService<Customer> {
    private final CustomerServiceClient customerServiceClient;

    public CustomerServiceImpl(CustomerServiceClient customerServiceClient) {
        this.customerServiceClient = customerServiceClient;
    }

    @Override
    public Customer get(long tc) {
        CustomerDto customerDto = customerServiceClient.get(tc);
        return customerDto.toCustomer();
    }
}
