package com.syrisa.onlinebank.microservice.accountservice.service.impl;

import com.syrisa.onlinebank.microservice.accountservice.entity.DemandDepositAccount;
import com.syrisa.onlinebank.microservice.accountservice.repository.DemandDepositAccountRepository;
import com.syrisa.onlinebank.microservice.accountservice.service.DemandDepositAccountService;
import com.syrisa.onlinebank.microservice.accountservice.utility.generate.account.Account;
import com.syrisa.onlinebank.microservice.accountservice.utility.generate.iban.Iban;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DemandDepositAccountServiceImpl implements DemandDepositAccountService {
    private final DemandDepositAccountRepository demandDepositAccountRepository;

    public DemandDepositAccountServiceImpl(DemandDepositAccountRepository demandDepositAccountRepository) {
        this.demandDepositAccountRepository = demandDepositAccountRepository;
    }

    @Override
    public DemandDepositAccount create(DemandDepositAccount demandDepositAccount) {
        String accountNumber = Account.generateAccount.get();
        demandDepositAccount.setAccountNumber(Long.parseLong(accountNumber));
        demandDepositAccount.setAccountIban(Iban.generateIban.apply(accountNumber));
        return demandDepositAccountRepository.save(demandDepositAccount);
    }

    @Override
    public DemandDepositAccount update(DemandDepositAccount demandDepositAccount) {
        if (get(demandDepositAccount.getAccountNumber()) != null) {
            return demandDepositAccountRepository.save(demandDepositAccount);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

    @Override
    public DemandDepositAccount get(long accountNumber) {
        return demandDepositAccountRepository.findById(accountNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
    }

    @Override
    public DemandDepositAccount getAccountByIBAN(String accountIBAN) {
        DemandDepositAccount demandDepositAccount = demandDepositAccountRepository.getDemandDepositAccountsByAccountIban(accountIBAN);
        if (demandDepositAccount != null) {
            return demandDepositAccount;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found.");
        }
    }

    @Override
    public List<DemandDepositAccount> getAccountByCustomers(long customerTC) {
        List<DemandDepositAccount> demandDepositAccounts = demandDepositAccountRepository.getDemandDepositAccountsByCustomerTC(customerTC);
        if (!demandDepositAccounts.isEmpty()) {
            return demandDepositAccounts;
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Customer have not accounts");
        }
    }

    @Override
    public String delete(long accountNumber) {
        DemandDepositAccount demandDepositAccount = get(accountNumber);
        if (demandDepositAccount.getAccountBalance() == 0) {
            demandDepositAccountRepository.delete(demandDepositAccount);
            return demandDepositAccount.getAccountNumber() + " number account was deleted.";
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Have money in your account");
        }
    }
}
