package com.syrisa.onlinebank.microservice.accountservice.controller;

import com.syrisa.onlinebank.microservice.accountservice.entity.SavingsAccount;
import com.syrisa.onlinebank.microservice.accountservice.service.SavingsAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SavingsAccountController {
    private final SavingsAccountService savingsAccountService;

    public SavingsAccountController(SavingsAccountService savingsAccountService) {
        this.savingsAccountService = savingsAccountService;
    }

    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccount create(@RequestBody SavingsAccount savingsAccount) {
        return savingsAccountService.create(savingsAccount);
    }

    @PutMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccount update(@RequestBody SavingsAccount savingsAccount){
        return savingsAccountService.update(savingsAccount);
    }

    @GetMapping("/savings/{accountNumber}")
    public SavingsAccount get(@PathVariable("accountNumber") long accountNumber){
        try {
            return savingsAccountService.get(accountNumber);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/savings/{accountIban}")
    public SavingsAccount getDemandDepositAccountByAccountIban(@PathVariable("accountIban") String accountIban) {
        try {
            return savingsAccountService.getAccountByIBAN(accountIban);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
    @GetMapping("/savings/{customerTC}")
    public List<SavingsAccount> getDemandDepositAccountByCustomerTC(@PathVariable("customerTC") long customerTC) {
        try {
            return savingsAccountService.getAccountByCustomers(customerTC);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/savings/{accountNumber}")
    public String delete(@PathVariable("accountNumber") long accountNumber) {
        try {
            return savingsAccountService.delete(accountNumber);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

}
