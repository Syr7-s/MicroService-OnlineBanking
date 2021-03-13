package com.syrisa.onlinebank.microservice.accountservice.service.impl;

import com.syrisa.onlinebank.microservice.accountservice.entity.SavingsAccount;
import com.syrisa.onlinebank.microservice.accountservice.service.SavingsAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {
    @Override
    public SavingsAccount create(SavingsAccount savingsAccount) {
        return null;
    }

    @Override
    public SavingsAccount update(SavingsAccount savingsAccount) {
        return null;
    }

    @Override
    public SavingsAccount get(long accountNumber) {
        return null;
    }

    @Override
    public SavingsAccount getAccountByIBAN(String accountIBAN) {
        return null;
    }

    @Override
    public List<SavingsAccount> getAccountByCustomers(long customerTC) {
        return null;
    }

    @Override
    public String delete(long accountNumber) {
        return null;
    }
}
