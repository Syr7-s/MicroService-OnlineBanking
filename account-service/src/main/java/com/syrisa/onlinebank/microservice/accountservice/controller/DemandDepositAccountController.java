package com.syrisa.onlinebank.microservice.accountservice.controller;

import com.syrisa.onlinebank.microservice.accountservice.entity.DemandDepositAccount;
import com.syrisa.onlinebank.microservice.accountservice.service.DemandDepositAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DemandDepositAccountController {
    private final DemandDepositAccountService demandDepositAccountService;

    public DemandDepositAccountController(DemandDepositAccountService demandDepositAccountService) {
        this.demandDepositAccountService = demandDepositAccountService;
    }

    @PostMapping("/demand")
    @ResponseStatus(HttpStatus.CREATED)
    public DemandDepositAccount create(@RequestBody DemandDepositAccount demandDepositAccount) {
        return demandDepositAccountService.create(demandDepositAccount);
    }

    @PutMapping("/demand")
    @ResponseStatus(HttpStatus.CREATED)
    public DemandDepositAccount update(@RequestBody DemandDepositAccount demandDepositAccount) {
        return demandDepositAccountService.update(demandDepositAccount);
    }

    @GetMapping("/demand/{accountNumber}")
    public DemandDepositAccount get(@PathVariable("accountNumber") long accountNumber) {
        try {
            return demandDepositAccountService.get(accountNumber);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/demand/{accountIban}")
    public DemandDepositAccount getDemandDepositAccountByAccountIban(@PathVariable("accountIban") String accountIban) {
        try {
            return demandDepositAccountService.getAccountByIBAN(accountIban);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/demands/{customerTC}")
    public List<DemandDepositAccount> getDemandDepositAccountByCustomerTC(@PathVariable("customerTC") long customerTC) {
        try {
            return demandDepositAccountService.getAccountByCustomers(customerTC);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/demand/{accountNumber}")
    public String delete(@PathVariable("accountNumber") long accountNumber) {
        try {
            return demandDepositAccountService.delete(accountNumber);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }
}
