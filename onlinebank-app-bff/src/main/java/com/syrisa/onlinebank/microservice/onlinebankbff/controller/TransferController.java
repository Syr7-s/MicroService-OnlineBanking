package com.syrisa.onlinebank.microservice.onlinebankbff.controller;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.DemandDepositAccountDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.dto.ExchangeDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.DemandDepositAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
public class TransferController {
    private final DemandDepositAccountService demandDepositAccountService;

    public TransferController(DemandDepositAccountService demandDepositAccountService) {
        this.demandDepositAccountService = demandDepositAccountService;
    }

    @PostMapping("/demand/transferMoney")
    public DemandDepositAccountDto moneyTransferBetweenAccounts(@RequestBody ExchangeDto exchangeDto) {
        try {
            return demandDepositAccountService.transferMoneyBetweenAccounts(exchangeDto.toExchange()).toDemandDepositAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @PostMapping("/demand/transfer/differentAccount")
    public DemandDepositAccountDto moneyTransferBetweenDifferentAccounts(@RequestBody ExchangeDto exchangeDto) {
        try {
            return demandDepositAccountService.transferMoneyDifferentAccounts(exchangeDto.toExchange()).toDemandDepositAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
