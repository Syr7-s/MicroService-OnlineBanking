package com.syrisa.onlinebank.microservice.accountservice.service.impl;

import com.syrisa.onlinebank.microservice.accountservice.entity.impl.DemandDepositAccount;
import com.syrisa.onlinebank.microservice.accountservice.entity.impl.Exchange;
import com.syrisa.onlinebank.microservice.accountservice.entity.impl.ExtractOfAccount;
import com.syrisa.onlinebank.microservice.accountservice.entity.impl.SavingsAccount;
import com.syrisa.onlinebank.microservice.accountservice.repository.DemandDepositAccountRepository;
import com.syrisa.onlinebank.microservice.accountservice.service.*;
import com.syrisa.onlinebank.microservice.accountservice.utility.generate.account.Account;
import com.syrisa.onlinebank.microservice.accountservice.utility.generate.iban.Iban;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Transactional
@Service
public class DemandDepositAccountServiceImpl implements DemandDepositAccountService,
        DepositAndWithdrawMoneyService<DemandDepositAccount, ExtractOfAccount> {
    private final DemandDepositAccountRepository demandDepositAccountRepository;
    private final ExtractOfAccountService<ExtractOfAccount> extractOfAccountService;
    private final ExchangeService<Exchange> exchangeService;
    private final SavingsAccountService savingsAccountService;

    public DemandDepositAccountServiceImpl(DemandDepositAccountRepository demandDepositAccountRepository, ExtractOfAccountService<ExtractOfAccount> extractOfAccountService, ExchangeService<Exchange> exchangeService, SavingsAccountService savingsAccountService) {
        this.demandDepositAccountRepository = demandDepositAccountRepository;
        this.extractOfAccountService = extractOfAccountService;
        this.exchangeService = exchangeService;
        this.savingsAccountService = savingsAccountService;
    }

    @Override
    public DemandDepositAccount create(DemandDepositAccount demandDepositAccount) {
        try {
            String accountNumber = Account.generateAccount.get();
            demandDepositAccount.setAccountNumber(Long.parseLong(accountNumber));
            demandDepositAccount.setAccountIban(Iban.generateIban.apply(accountNumber));
            return demandDepositAccountRepository.save(demandDepositAccount);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error");
        }
    }

    @Override
    public DemandDepositAccount update(DemandDepositAccount demandDepositAccount) {
        try {
            if (get(demandDepositAccount.getAccountNumber()) != null) {
                return demandDepositAccountRepository.save(demandDepositAccount);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error.");
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
    public Page<DemandDepositAccount> getAccounts(Pageable pageable) {
        return demandDepositAccountRepository.findAllBy(pageable);
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

    @Override
    public DemandDepositAccount depositMoneyAccount(ExtractOfAccount extractOfAccount) {
        try {
            DemandDepositAccount demandDepositAccount = get(extractOfAccount.getAccountNumber());
            demandDepositAccount.setAccountBalance(demandDepositAccount.getAccountBalance() + extractOfAccount.getMoney());
            addAccountTypeAndAccountProcess("Deposit money", extractOfAccount);
            extractOfAccountService.create(extractOfAccount);
            return update(demandDepositAccount);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error.");
        }
    }

    @Override
    public DemandDepositAccount withDrawMoneyAccount(ExtractOfAccount extractOfAccount) {
        try {
            DemandDepositAccount demandDepositAccount = get(extractOfAccount.getAccountNumber());
            if (demandDepositAccount.getAccountBalance() - extractOfAccount.getMoney() > 0) {
                demandDepositAccount.setAccountBalance(demandDepositAccount.getAccountBalance() - extractOfAccount.getMoney());
                addAccountTypeAndAccountProcess("With Draw money", extractOfAccount);
                extractOfAccountService.create(extractOfAccount);
                return update(demandDepositAccount);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Not enough money in your account.");
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    private void addAccountTypeAndAccountProcess(String accountProcess, ExtractOfAccount extractOfAccount) {
        try {
            extractOfAccount.setAccountType("Demand Deposit Account");
            extractOfAccount.setAccountProcess(accountProcess);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public DemandDepositAccount transferMoneyBetweenAccounts(Exchange exchange) {
        try {
            DemandDepositAccount from = getAccountByIBAN(exchange.getFromAccountIban());
            DemandDepositAccount to = getAccountByIBAN(exchange.getToAccountIban());
            if (from.getAccountBalance() - exchange.getDepositMoney() > 0) {
                from.setAccountBalance(from.getAccountBalance() - exchange.getDepositMoney());
                to.setAccountBalance(to.getAccountBalance() + exchange.getReceiveMoney());
                exchange.setProcessType("Transfer between accounts.");
                exchangeService.createExchange(exchange);
                demandDepositAccountRepository.save(from);
                return demandDepositAccountRepository.save(to);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Not enough money in your account");
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public DemandDepositAccount transferMoneyDifferentAccounts(Exchange exchange) {
        try {
            DemandDepositAccount from = getAccountByIBAN(exchange.getFromAccountIban());
            SavingsAccount to = savingsAccountService.getAccountByIBAN(exchange.getToAccountIban());
            if (from.getAccountBalance() - exchange.getDepositMoney() > 0) {
                from.setAccountBalance(from.getAccountBalance() - exchange.getDepositMoney());
                to.setAccountBalance(to.getAccountBalance() + exchange.getReceiveMoney());
                exchange.setProcessType("Transfer between different accounts.");
                exchangeService.createExchange(exchange);
                savingsAccountService.update(to);
                return demandDepositAccountRepository.save(from);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Not enough money in your account");
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
