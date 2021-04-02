package com.syrisa.onlinebank.microservice.onlinebankbff.service.impl;

import com.syrisa.onlinebank.microservice.onlinebankbff.client.*;
import com.syrisa.onlinebank.microservice.onlinebankbff.dto.*;
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
    private final BankCardServiceClient bankCardServiceClient;
    private final CreditCardServiceClient creditCardServiceClient;

    public CustomerServiceImpl(CustomerServiceClient customerServiceClient, DemandDepositAccountServiceClient demandDepositAccountServiceClient, SavingsAccountServiceClient savingsAccountServiceClient, BankCardServiceClient bankCardServiceClient, CreditCardServiceClient creditCardServiceClient) {
        this.customerServiceClient = customerServiceClient;
        this.demandDepositAccountServiceClient = demandDepositAccountServiceClient;
        this.savingsAccountServiceClient = savingsAccountServiceClient;
        this.bankCardServiceClient = bankCardServiceClient;
        this.creditCardServiceClient = creditCardServiceClient;
    }

    @Override
    public Customer get(long tc) {
        CustomerDto customerDto = customerServiceClient.get(tc);
        List<DemandDepositAccountDto> demandDepositAccounts = demandDepositAccountServiceClient.getDemandDepositAccountByCustomerTC(tc);
        List<SavingsAccountDto> savingsAccounts = savingsAccountServiceClient.getSavingsAccountByCustomerTC(tc);
        List<BankCardDto> bankCards = bankCardServiceClient.getCardByUserID(tc);
        List<CreditCardDto> creditCards = creditCardServiceClient.getCardByUserID(tc);
        customerDto.setDemandDepositAccounts(demandDepositAccounts.stream().map(DemandDepositAccountDto::toDemandDepositAccount).collect(Collectors.toList()));
        customerDto.setSavingsAccounts(savingsAccounts.stream().map(SavingsAccountDto::toSavingsAccount).collect(Collectors.toList()));
        customerDto.setBankCards(bankCards.stream().map(BankCardDto::toBankCard).collect(Collectors.toList()));
        customerDto.setCreditCards(creditCards.stream().map(CreditCardDto::toCreditCard).collect(Collectors.toList()));
        return customerDto.toCustomer();
    }
}
