package com.syrisa.onlinebank.microservice.onlinebankbff.controller;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.SavingsAccountDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.SavingsAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
public class SavingsAccountController {
    private final SavingsAccountService savingsAccountService;

    public SavingsAccountController(SavingsAccountService savingsAccountService) {
        this.savingsAccountService = savingsAccountService;
    }

    @GetMapping("/savings")
    public SavingsAccountDto getSavingsAccountByAccountIban(@RequestParam String accountIban){
        try{
            return savingsAccountService.getAccountByIban(accountIban).toSavingsAccountDto();
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage());
        }
    }
}
