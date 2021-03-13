package com.syrisa.onlinebank.microservice.accountservice.service.impl;

import com.syrisa.onlinebank.microservice.accountservice.entity.SavingsAccount;
import com.syrisa.onlinebank.microservice.accountservice.repository.SavingsAccountRepository;
import com.syrisa.onlinebank.microservice.accountservice.service.SavingsAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {
    private final SavingsAccountRepository savingsAccountRepository;

    public SavingsAccountServiceImpl(SavingsAccountRepository savingsAccountRepository) {
        this.savingsAccountRepository = savingsAccountRepository;
    }

    @Override
    public SavingsAccount create(SavingsAccount savingsAccount) {
        return savingsAccountRepository.save(savingsAccount);
    }

    @Override
    public SavingsAccount update(SavingsAccount savingsAccount) {
        if (get(savingsAccount.getAccountNumber()) != null) {
            return savingsAccountRepository.save(savingsAccount);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer have not account");
        }
    }

    @Override
    public SavingsAccount get(long accountNumber) {
        return savingsAccountRepository.findById(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
    }

    @Override
    public SavingsAccount getAccountByIBAN(String accountIBAN) {
        SavingsAccount savingsAccount = savingsAccountRepository.getSavingsAccountByAccountIban(accountIBAN);
        if (savingsAccount != null) {
            return savingsAccount;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

    @Override
    public List<SavingsAccount> getAccountByCustomers(long customerTC) {
        List<SavingsAccount> savingsAccounts = savingsAccountRepository.getSavingsAccountsByCustomerTC(customerTC);
        if (!savingsAccounts.isEmpty()) {
            return savingsAccounts;
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Customer have not accounts");
        }
    }

    @Override
    public String delete(long accountNumber) {
        SavingsAccount savingsAccount = get(accountNumber);
        if (savingsAccount.getAccountBalance() == 0) {
            savingsAccountRepository.delete(savingsAccount);
            return savingsAccount.getAccountNumber() + " number account was deleted.";
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Have money in your account");
        }
    }
}
