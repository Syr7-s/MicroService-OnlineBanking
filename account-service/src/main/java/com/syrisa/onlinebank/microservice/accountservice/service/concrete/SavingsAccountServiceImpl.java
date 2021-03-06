package com.syrisa.onlinebank.microservice.accountservice.service.concrete;

import com.syrisa.onlinebank.microservice.accountservice.entity.impl.ExtractOfAccount;
import com.syrisa.onlinebank.microservice.accountservice.entity.impl.SavingsAccount;
import com.syrisa.onlinebank.microservice.accountservice.repository.SavingsAccountRepository;
import com.syrisa.onlinebank.microservice.accountservice.service.abstrct.DepositAndWithdrawMoneyService;
import com.syrisa.onlinebank.microservice.accountservice.service.abstrct.ExtractOfAccountService;
import com.syrisa.onlinebank.microservice.accountservice.service.abstrct.SavingsAccountService;
import com.syrisa.onlinebank.microservice.accountservice.utility.generate.account.Account;
import com.syrisa.onlinebank.microservice.accountservice.utility.generate.iban.Iban;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class SavingsAccountServiceImpl implements SavingsAccountService,
        DepositAndWithdrawMoneyService<SavingsAccount, ExtractOfAccount> {
    private final SavingsAccountRepository savingsAccountRepository;
    private final ExtractOfAccountService<ExtractOfAccount> extractOfAccountService;

    public SavingsAccountServiceImpl(SavingsAccountRepository savingsAccountRepository, ExtractOfAccountService<ExtractOfAccount> extractOfAccountService) {
        this.savingsAccountRepository = savingsAccountRepository;
        this.extractOfAccountService = extractOfAccountService;
    }

    @Override
    public SavingsAccount create(SavingsAccount savingsAccount) {
        try {
            String accountNumber = Account.generateAccount.get();
            savingsAccount.setAccountNumber(Long.parseLong(accountNumber));
            savingsAccount.setAccountIban(Iban.generateIban.apply(accountNumber));
            return savingsAccountRepository.save(savingsAccount);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }

    }

    @Override
    public SavingsAccount update(SavingsAccount savingsAccount) {
        try {
            if (get(savingsAccount.getAccountNumber()) != null) {
                return savingsAccountRepository.save(savingsAccount);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer have not account");
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
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
    public Page<SavingsAccount> getAccounts(Pageable pageable) {
        return savingsAccountRepository.findAllBy(pageable);
    }

    @Override
    public List<SavingsAccount> getAccountByCustomers(long customerTC) {
        return savingsAccountRepository.getSavingsAccountsByCustomerTC(customerTC);
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

    @Transactional
    @Override
    public SavingsAccount depositMoneyAccount(ExtractOfAccount extractOfAccount) {
        try {
            SavingsAccount savingsAccount = get(extractOfAccount.getAccountNumber());
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance() + extractOfAccount.getMoney());
            addAccountTypeAndAccountProcess("Deposit Money", extractOfAccount);
            extractOfAccountService.create(extractOfAccount);
            return update(savingsAccount);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Transactional
    @Override
    public SavingsAccount withDrawMoneyAccount(ExtractOfAccount extractOfAccount) {
        try {
            SavingsAccount savingsAccount = get(extractOfAccount.getAccountNumber());
            if (savingsAccount.getAccountBalance() - extractOfAccount.getMoney() > 0) {
                savingsAccount.setAccountBalance(savingsAccount.getAccountBalance() - extractOfAccount.getMoney());
                addAccountTypeAndAccountProcess("With Draw Money", extractOfAccount);
                extractOfAccountService.create(extractOfAccount);
                return update(savingsAccount);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Not enough money.");
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    private void addAccountTypeAndAccountProcess(String accountProcess, ExtractOfAccount extractOfAccount) {
        try {
            extractOfAccount.setAccountType("Savings Account");
            extractOfAccount.setAccountProcess(accountProcess);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
