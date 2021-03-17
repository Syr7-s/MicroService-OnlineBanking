package com.syrisa.onlinebank.microservice.onlinebankbff.service.impl;

import com.syrisa.onlinebank.microservice.onlinebankbff.client.AccountProcessClient;
import com.syrisa.onlinebank.microservice.onlinebankbff.client.SavingsAccountServiceClient;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.ExtractOfAccount;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.SavingsAccount;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.DepositAndWithdrawMoneyService;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.SavingsAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService
        , DepositAndWithdrawMoneyService<SavingsAccount, ExtractOfAccount> {
    private final SavingsAccountServiceClient savingsAccountServiceClient;
    private final AccountProcessClient accountProcessClient;

    public SavingsAccountServiceImpl(SavingsAccountServiceClient savingsAccountServiceClient, AccountProcessClient accountProcessClient) {
        this.savingsAccountServiceClient = savingsAccountServiceClient;
        this.accountProcessClient = accountProcessClient;
    }

    @Override
    public SavingsAccount getAccountByIban(String accountIban) {
        try {
            return savingsAccountServiceClient.getSavingsAccountByAccountIban(accountIban).toSavingsAccount();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Not found.");
        }
    }

    @Override
    public SavingsAccount depositMoneyAccount(ExtractOfAccount extractOfAccount) {
        try {
            return accountProcessClient.depositMoneySavings(extractOfAccount.toExtractOfAccountDto()).toSavingsAccount();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public SavingsAccount withDrawMoneyAccount(ExtractOfAccount extractOfAccount) {
        try {
            return accountProcessClient.withDrawMoneySavings(extractOfAccount.toExtractOfAccountDto()).toSavingsAccount();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
