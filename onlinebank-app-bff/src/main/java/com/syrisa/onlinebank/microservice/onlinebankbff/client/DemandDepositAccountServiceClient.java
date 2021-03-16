package com.syrisa.onlinebank.microservice.onlinebankbff.client;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.DemandDepositAccountDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.dto.ExtractOfAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("account-service")
public interface DemandDepositAccountServiceClient {
    @GetMapping("/api/v1/demands/{customerTC}")
    List<DemandDepositAccountDto> getDemandDepositAccountByCustomerTC(@PathVariable("customerTC") long customerTC);

    @GetMapping("/api/v1/demand/iban/{accountIban}")
    DemandDepositAccountDto getDemandDepositAccountByAccountIban(@PathVariable("accountIban") String accountIban);

    @PostMapping("/api/v1/demand/depositMoney")
    DemandDepositAccountDto depositMoneyDemand(@RequestBody ExtractOfAccountDto extractOfAccountDto);
}
