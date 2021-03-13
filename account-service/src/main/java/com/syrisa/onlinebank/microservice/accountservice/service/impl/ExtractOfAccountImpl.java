package com.syrisa.onlinebank.microservice.accountservice.service.impl;

import com.syrisa.onlinebank.microservice.accountservice.entity.DemandDepositAccount;
import com.syrisa.onlinebank.microservice.accountservice.entity.ExtractOfAccount;
import com.syrisa.onlinebank.microservice.accountservice.entity.SavingsAccount;
import com.syrisa.onlinebank.microservice.accountservice.repository.ExtractOfAccountRepository;
import com.syrisa.onlinebank.microservice.accountservice.service.ExtractOfAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class ExtractOfAccountImpl implements ExtractOfAccountService {
    private final ExtractOfAccountRepository extractOfAccountRepository;
    private final DemandDepositAccountServiceImpl demandDepositAccountService;
    private final SavingsAccountServiceImpl savingsAccountService;

    public ExtractOfAccountImpl(ExtractOfAccountRepository extractOfAccountRepository, DemandDepositAccountServiceImpl demandDepositAccountService, SavingsAccountServiceImpl savingsAccountService) {
        this.extractOfAccountRepository = extractOfAccountRepository;
        this.demandDepositAccountService = demandDepositAccountService;
        this.savingsAccountService = savingsAccountService;
    }

    @Transactional
    @Override
    public DemandDepositAccount depositMoneyDemandDepositAccount(ExtractOfAccount extractOfAccount) {
        try {
            DemandDepositAccount demandDepositAccount = demandDepositAccountService.get(extractOfAccount.getAccountNumber());
            demandDepositAccount.setAccountBalance(demandDepositAccount.getAccountBalance() + extractOfAccount.getMoney());
            extractOfAccount.setId(UUID.randomUUID());
            getDataAndTime(extractOfAccount);
            extractOfAccountRepository.save(extractOfAccount);
            return demandDepositAccountService.update(demandDepositAccount).toDemandDepositAccountDto().toDemandDepositAccount();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error.");
        }
    }

    @Override
    public DemandDepositAccount withDrawMoneyDemandDepositAccount(ExtractOfAccount extractOfAccount) {
        try {
            DemandDepositAccount demandDepositAccount = demandDepositAccountService.get(extractOfAccount.getAccountNumber());
            if (demandDepositAccount.getAccountBalance() - extractOfAccount.getMoney() > 0) {
                demandDepositAccount.setAccountBalance(demandDepositAccount.getAccountBalance() - extractOfAccount.getMoney());
                extractOfAccount.setId(UUID.randomUUID());
                getDataAndTime(extractOfAccount);
                extractOfAccountRepository.save(extractOfAccount);
                return demandDepositAccountService.update(demandDepositAccount).toDemandDepositAccountDto().toDemandDepositAccount();
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Not enough money in your account.");
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public SavingsAccount depositMoneySavingsAccount(ExtractOfAccount extractOfAccount) {
        try {
            SavingsAccount savingsAccount = savingsAccountService.get(extractOfAccount.getAccountNumber());
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance() + extractOfAccount.getMoney());
            extractOfAccount.setId(UUID.randomUUID());
            getDataAndTime(extractOfAccount);
            extractOfAccountRepository.save(extractOfAccount);
            return savingsAccountService.update(savingsAccount).toSavingsAccountDto().toSavingsAccount();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Transactional
    @Override
    public SavingsAccount withDrawMoneySavingsAccount(ExtractOfAccount extractOfAccount) {
        try {
            SavingsAccount savingsAccount = savingsAccountService.get(extractOfAccount.getAccountNumber());
            if (savingsAccount.getAccountBalance() - extractOfAccount.getMoney() > 0) {
                savingsAccount.setAccountBalance(savingsAccount.getAccountBalance() - extractOfAccount.getMoney());
                extractOfAccount.setId(UUID.randomUUID());
                getDataAndTime(extractOfAccount);
                extractOfAccountRepository.save(extractOfAccount);
                return savingsAccountService.update(savingsAccount).toSavingsAccountDto().toSavingsAccount();
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Not enough money.");
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }

    }

    private void getDataAndTime(ExtractOfAccount extractOfAccount) {
        extractOfAccount.setTime(LocalTime.now());
        extractOfAccount.setDate(LocalDate.now());
    }

}
