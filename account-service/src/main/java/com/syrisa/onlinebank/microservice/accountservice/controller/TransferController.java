package com.syrisa.onlinebank.microservice.accountservice.controller;

import com.syrisa.onlinebank.microservice.accountservice.dto.DemandDepositAccountDto;
import com.syrisa.onlinebank.microservice.accountservice.dto.ExchangeDto;
import com.syrisa.onlinebank.microservice.accountservice.entity.impl.Exchange;
import com.syrisa.onlinebank.microservice.accountservice.service.DemandDepositAccountService;
import com.syrisa.onlinebank.microservice.accountservice.service.ExchangeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class TransferController {
    private final ExchangeService<Exchange> exchangeService;
    private final DemandDepositAccountService demandDepositAccountService;

    public TransferController(ExchangeService<Exchange> exchangeService, DemandDepositAccountService demandDepositAccountService) {
        this.exchangeService = exchangeService;
        this.demandDepositAccountService = demandDepositAccountService;
    }

    @PostMapping("/demand/transfer/betweenAccount")
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

    @GetMapping("/exchanges")
    public List<ExchangeDto> getExchanges() {
        return exchangeService.getAllExchanges()
                .stream()
                .map(Exchange::toExchangeDto)
                .collect(Collectors.toList());
    }

}
