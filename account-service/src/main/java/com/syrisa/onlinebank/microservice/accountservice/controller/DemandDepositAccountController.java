package com.syrisa.onlinebank.microservice.accountservice.controller;

import com.syrisa.onlinebank.microservice.accountservice.dto.DemandDepositAccountDto;
import com.syrisa.onlinebank.microservice.accountservice.entity.impl.DemandDepositAccount;
import com.syrisa.onlinebank.microservice.accountservice.service.DemandDepositAccountService;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class DemandDepositAccountController {
    private final DemandDepositAccountService demandDepositAccountService;

    public DemandDepositAccountController(DemandDepositAccountService demandDepositAccountService) {
        this.demandDepositAccountService = demandDepositAccountService;
    }

    @PostMapping("/demand")
    @ResponseStatus(HttpStatus.CREATED)
    public DemandDepositAccountDto create(@RequestBody DemandDepositAccountDto demandDepositAccountDto) {
        try {
            return demandDepositAccountService.create(demandDepositAccountDto.toDemandDepositAccount()).toDemandDepositAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @PutMapping("/demand")
    @ResponseStatus(HttpStatus.CREATED)
    public DemandDepositAccountDto update(@RequestBody DemandDepositAccountDto demandDepositAccountDto) {
        try {
            return demandDepositAccountService.update(demandDepositAccountDto.toDemandDepositAccount()).toDemandDepositAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping("/demand/accountNumber/{accountNumber}")
    public DemandDepositAccountDto get(@PathVariable("accountNumber") long accountNumber) {
        try {
            return demandDepositAccountService.get(accountNumber).toDemandDepositAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/demand/iban/{accountIban}")
    public DemandDepositAccountDto getDemandDepositAccountByAccountIban(@PathVariable("accountIban") String accountIban) {
        try {
            return demandDepositAccountService.getAccountByIBAN(accountIban).toDemandDepositAccountDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/demands/{customerTC}")
    public List<DemandDepositAccountDto> getDemandDepositAccountByCustomerTC(@PathVariable("customerTC") long customerTC) {
        try {
            return demandDepositAccountService.getAccountByCustomers(customerTC)
                    .stream()
                    .map(DemandDepositAccount::toDemandDepositAccountDto)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping(value = "/demands", params = {"page", "size"})
    public List<DemandDepositAccountDto> getDemandDepositAccounts(@Min(value = 0) @RequestParam int page, @Min(value = 1) @RequestParam int size) {
        try {
            return demandDepositAccountService.getAccounts(PageRequest.of(page, size))
                    .stream()
                    .map(DemandDepositAccount::toDemandDepositAccountDto)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
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
