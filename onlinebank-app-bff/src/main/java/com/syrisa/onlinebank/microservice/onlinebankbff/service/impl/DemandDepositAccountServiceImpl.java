package com.syrisa.onlinebank.microservice.onlinebankbff.service.impl;

import com.syrisa.onlinebank.microservice.onlinebankbff.client.DemandDepositAccountServiceClient;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.DemandDepositAccount;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.DemandDepositAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DemandDepositAccountServiceImpl implements DemandDepositAccountService {
    private final DemandDepositAccountServiceClient demandDepositAccountServiceClient;

    public DemandDepositAccountServiceImpl(DemandDepositAccountServiceClient demandDepositAccountServiceClient) {
        this.demandDepositAccountServiceClient = demandDepositAccountServiceClient;
    }

    @Override
    public DemandDepositAccount getAccountByIban(String accountIban) {
        try {
            return demandDepositAccountServiceClient.getDemandDepositAccountByAccountIban(accountIban).toDemandDepositAccount();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found.");
        }

    }
}
