package com.syrisa.onlinebank.microservice.accountservice.controller;

import com.syrisa.onlinebank.microservice.accountservice.dto.DemandDepositAccountDto;
import com.syrisa.onlinebank.microservice.accountservice.dto.ExchangeDto;
import com.syrisa.onlinebank.microservice.accountservice.dto.ExtractOfAccountDto;
import com.syrisa.onlinebank.microservice.accountservice.dto.SavingsAccountDto;
import com.syrisa.onlinebank.microservice.accountservice.entity.impl.DemandDepositAccount;
import com.syrisa.onlinebank.microservice.accountservice.entity.impl.ExtractOfAccount;
import com.syrisa.onlinebank.microservice.accountservice.entity.impl.SavingsAccount;
import com.syrisa.onlinebank.microservice.accountservice.service.DemandDepositAccountService;
import com.syrisa.onlinebank.microservice.accountservice.service.DepositAndWithdrawMoneyService;
import com.syrisa.onlinebank.microservice.accountservice.service.ExtractOfAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class AccountProcessController {
    private final ExtractOfAccountService<ExtractOfAccount> extractOfAccountService;
    private final DemandDepositAccountService demandDepositAccountService;
    private final DepositAndWithdrawMoneyService<DemandDepositAccount, ExtractOfAccount> demandDeposit;
    private final DepositAndWithdrawMoneyService<SavingsAccount, ExtractOfAccount> savings;

    public AccountProcessController(ExtractOfAccountService<ExtractOfAccount> extractOfAccountService
            , DemandDepositAccountService demandDepositAccountService, DepositAndWithdrawMoneyService<DemandDepositAccount, ExtractOfAccount> demandDeposit,
                                    DepositAndWithdrawMoneyService<SavingsAccount, ExtractOfAccount> savings) {
        this.extractOfAccountService = extractOfAccountService;
        this.demandDepositAccountService = demandDepositAccountService;
        this.demandDeposit = demandDeposit;
        this.savings = savings;
    }

    @PostMapping("/demand/depositMoney")
    @ResponseStatus(HttpStatus.CREATED)
    public DemandDepositAccountDto depositMoneyDemand(@RequestBody ExtractOfAccountDto extractOfAccountDto) {
        try {
            return demandDeposit.depositMoneyAccount(extractOfAccountDto.toExtractOfAccount()).toDemandDepositAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @PostMapping("/demand/withDrawMoney")
    @ResponseStatus(HttpStatus.CREATED)
    public DemandDepositAccountDto withDrawMoneyDemand(@RequestBody ExtractOfAccountDto extractOfAccountDto) {
        try {
            return demandDeposit.withDrawMoneyAccount(extractOfAccountDto.toExtractOfAccount()).toDemandDepositAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @PostMapping("/savings/depositMoney")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccountDto depositMoneySavings(@RequestBody ExtractOfAccountDto extractOfAccountDto) {
        try {
            return savings.depositMoneyAccount(extractOfAccountDto.toExtractOfAccount()).toSavingsAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @PostMapping("/savings/withDrawMoney")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccountDto withDrawMoneySavings(@RequestBody ExtractOfAccountDto extractOfAccountDto) {
        try {
            return savings.withDrawMoneyAccount(extractOfAccountDto.toExtractOfAccount()).toSavingsAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
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
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping("/account/process")
    public List<ExtractOfAccountDto> getAllProcess() {
        return extractOfAccountService.getAllProcess()
                .stream()
                .map(ExtractOfAccount::toExtractOfAccountDto)
                .collect(Collectors.toList());
    }

}
