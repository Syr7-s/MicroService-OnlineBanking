package com.syrisa.onlinebank.microservice.onlinebankbff.service.impl;

import com.syrisa.onlinebank.microservice.onlinebankbff.client.AccountProcessClient;
import com.syrisa.onlinebank.microservice.onlinebankbff.client.DemandDepositAccountServiceClient;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.DemandDepositAccount;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.ExtractOfAccount;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.DemandDepositAccountService;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.DepositAndWithdrawMoneyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DemandDepositAccountServiceImpl implements DemandDepositAccountService
        , DepositAndWithdrawMoneyService<DemandDepositAccount, ExtractOfAccount> {
    private final DemandDepositAccountServiceClient demandDepositAccountServiceClient;
    private final AccountProcessClient accountProcessClient;

    public DemandDepositAccountServiceImpl(DemandDepositAccountServiceClient demandDepositAccountServiceClient, AccountProcessClient accountProcessClient) {
        this.demandDepositAccountServiceClient = demandDepositAccountServiceClient;
        this.accountProcessClient = accountProcessClient;
    }

    @Override
    public DemandDepositAccount getAccountByIban(String accountIban) {
        try {
            return demandDepositAccountServiceClient.getDemandDepositAccountByAccountIban(accountIban).toDemandDepositAccount();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found.");
        }

    }

    @Override
    public DemandDepositAccount depositMoneyAccount(ExtractOfAccount extractOfAccount) {
        try {
            return accountProcessClient.depositMoneyDemand(extractOfAccount.toExtractOfAccountDto()).toDemandDepositAccount();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public DemandDepositAccount withDrawMoneyAccount(ExtractOfAccount extractOfAccount) {
        try {
            return accountProcessClient.withDrawMoneyDemand(extractOfAccount.toExtractOfAccountDto()).toDemandDepositAccount();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
