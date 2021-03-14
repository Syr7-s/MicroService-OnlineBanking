package com.syrisa.onlinebank.microservice.accountservice.service.impl;

import com.syrisa.onlinebank.microservice.accountservice.entity.ExtractOfAccount;
import com.syrisa.onlinebank.microservice.accountservice.repository.ExtractOfAccountRepository;
import com.syrisa.onlinebank.microservice.accountservice.service.ExtractOfAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class ExtractOfAccountImpl implements ExtractOfAccountService<ExtractOfAccount> {
    private final ExtractOfAccountRepository extractOfAccountRepository;

    public ExtractOfAccountImpl(ExtractOfAccountRepository extractOfAccountRepository) {
        this.extractOfAccountRepository = extractOfAccountRepository;
    }

    @Override
    public void create(ExtractOfAccount extractOfAccount) {
        try {
            extractOfAccount.setId(UUID.randomUUID());
            extractOfAccount.setTime(LocalTime.now());
            extractOfAccount.setDate(LocalDate.now());
            extractOfAccountRepository.save(extractOfAccount);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public List<ExtractOfAccount> getAllProcess() {
        List<ExtractOfAccount> extractOfAccounts = extractOfAccountRepository.findAll();
        if (!extractOfAccounts.isEmpty()) {
            return extractOfAccounts;
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Extract of Account is empty.");
        }
    }

}
