package com.syrisa.onlinebank.microservice.onlinebankbff.controller;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.DemandDepositAccountDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.DemandDepositAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
public class DemandDepositAccountController {
    private final DemandDepositAccountService demandDepositAccountService;

    public DemandDepositAccountController(DemandDepositAccountService demandDepositAccountService) {
        this.demandDepositAccountService = demandDepositAccountService;
    }

    @GetMapping("/demand")
    public DemandDepositAccountDto getDemandDepositAccountByAccountIban(@RequestParam String accountIban) {
        try {
            return demandDepositAccountService.getAccountByIban(accountIban).toDemandDepositAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
