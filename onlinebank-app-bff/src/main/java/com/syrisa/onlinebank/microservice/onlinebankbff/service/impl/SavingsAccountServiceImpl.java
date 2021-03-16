package com.syrisa.onlinebank.microservice.onlinebankbff.service.impl;

import com.syrisa.onlinebank.microservice.onlinebankbff.client.SavingsAccountServiceClient;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.SavingsAccount;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.SavingsAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {
    private final SavingsAccountServiceClient savingsAccountServiceClient;

    public SavingsAccountServiceImpl(SavingsAccountServiceClient savingsAccountServiceClient) {
        this.savingsAccountServiceClient = savingsAccountServiceClient;
    }

    @Override
    public SavingsAccount getAccountByIban(String accountIban) {
        try{
            return savingsAccountServiceClient.getDemandDepositAccountByAccountIban(accountIban).toSavingsAccount();
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account Not found.");
        }
    }
}
