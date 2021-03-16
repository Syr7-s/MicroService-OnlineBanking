package com.syrisa.onlinebank.microservice.onlinebankbff.service.impl;

import com.syrisa.onlinebank.microservice.onlinebankbff.client.AccountProcessClient;
import com.syrisa.onlinebank.microservice.onlinebankbff.dto.ExtractOfAccountDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.ExtractOfAccount;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.ExtractOfAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExtractOfAccountServiceImpl implements ExtractOfAccountService<ExtractOfAccount> {
    private final AccountProcessClient accountProcessClient;

    public ExtractOfAccountServiceImpl(AccountProcessClient accountProcessClient) {
        this.accountProcessClient = accountProcessClient;
    }

    @Override
    public List<ExtractOfAccount> getAllProcess() {
        try{
            return accountProcessClient.getAllProcess()
                    .stream()
                    .map(ExtractOfAccountDto::toExtractOfAccount)
                    .collect(Collectors.toList());
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Server Error");
        }
    }
}
