package com.syrisa.onlinebank.microservice.onlinebankbff.service.impl;

import com.syrisa.onlinebank.microservice.onlinebankbff.client.*;
import com.syrisa.onlinebank.microservice.onlinebankbff.dto.CustomerDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.dto.DemandDepositAccountDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.dto.SavingsAccountDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.Customer;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService<Customer> {
    private final CustomerServiceClient customerServiceClient;
    private final DemandDepositAccountServiceClient demandDepositAccountServiceClient;
    private final SavingsAccountServiceClient savingsAccountServiceClient;

    public CustomerServiceImpl(CustomerServiceClient customerServiceClient, DemandDepositAccountServiceClient demandDepositAccountServiceClient, SavingsAccountServiceClient savingsAccountServiceClient) {
        this.customerServiceClient = customerServiceClient;
        this.demandDepositAccountServiceClient = demandDepositAccountServiceClient;
        this.savingsAccountServiceClient = savingsAccountServiceClient;
    }

    @Override
    public Customer get(long tc) {
        CustomerDto customerDto = customerServiceClient.get(tc);
        List<DemandDepositAccountDto> demandDepositAccounts = demandDepositAccountServiceClient.getDemandDepositAccountByCustomerTC(tc);
        List<SavingsAccountDto> savingsAccounts = savingsAccountServiceClient.getSavingsAccountByCustomerTC(tc);
        customerDto.setDemandDepositAccounts(demandDepositAccounts.stream().map(DemandDepositAccountDto::toDemandDepositAccount).collect(Collectors.toList()));
        customerDto.setSavingsAccounts(savingsAccounts.stream().map(SavingsAccountDto::toSavingsAccount).collect(Collectors.toList()));
        return customerDto.toCustomer();
    }
}
