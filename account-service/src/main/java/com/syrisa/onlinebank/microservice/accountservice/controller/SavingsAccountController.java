package com.syrisa.onlinebank.microservice.accountservice.controller;

import com.syrisa.onlinebank.microservice.accountservice.dto.SavingsAccountDto;
import com.syrisa.onlinebank.microservice.accountservice.entity.impl.SavingsAccount;
import com.syrisa.onlinebank.microservice.accountservice.service.abstrct.SavingsAccountService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class SavingsAccountController {
    private final SavingsAccountService savingsAccountService;

    public SavingsAccountController(SavingsAccountService savingsAccountService) {
        this.savingsAccountService = savingsAccountService;
    }

    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccountDto create(@RequestBody SavingsAccountDto savingsAccountDto) {
        try {
            return savingsAccountService.create(savingsAccountDto.toSavingsAccount()).toSavingsAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }

    }

    @PutMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccountDto update(@RequestBody SavingsAccountDto savingsAccountDto) {
        try {
            return savingsAccountService.update(savingsAccountDto.toSavingsAccount()).toSavingsAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping("/savings/accountNumber/{accountNumber}")
    public SavingsAccountDto get(@PathVariable("accountNumber") long accountNumber) {
        try {
            return savingsAccountService.get(accountNumber).toSavingsAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/savings/iban/{accountIban}")
    public SavingsAccountDto getSavingsAccountByAccountIban(@PathVariable("accountIban") String accountIban) {
        try {
            return savingsAccountService.getAccountByIBAN(accountIban).toSavingsAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/savings/{customerTC}")
    public List<SavingsAccountDto> getSavingsAccountByCustomerTC(@PathVariable("customerTC") long customerTC) {
        try {
            return savingsAccountService.getAccountByCustomers(customerTC)
                    .stream()
                    .map(SavingsAccount::toSavingsAccountDto)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping(value = "/savings/all", params = {"page", "size"})
    public List<SavingsAccountDto> getAccounts(@Min(value = 0) @RequestParam int page, @Min(value = 1) @RequestParam int size) {
        try {
            return savingsAccountService.getAccounts(PageRequest.of(page, size))
                    .stream()
                    .map(SavingsAccount::toSavingsAccountDto)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
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
