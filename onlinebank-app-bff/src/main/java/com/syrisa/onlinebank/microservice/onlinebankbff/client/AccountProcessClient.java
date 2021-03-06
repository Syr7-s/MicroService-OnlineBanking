package com.syrisa.onlinebank.microservice.onlinebankbff.client;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.DemandDepositAccountDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.dto.ExtractOfAccountDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.dto.SavingsAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;


@FeignClient("account-service")
public interface AccountProcessClient {
    @PostMapping("/api/v1/demand/depositMoney")
    DemandDepositAccountDto depositMoneyDemand(@RequestBody ExtractOfAccountDto extractOfAccountDto);

    @PostMapping("/api/v1/demand/withDrawMoney")
    DemandDepositAccountDto withDrawMoneyDemand(@RequestBody ExtractOfAccountDto extractOfAccountDto);

    @PostMapping("/api/v1/savings/depositMoney")
    SavingsAccountDto depositMoneySavings(@RequestBody ExtractOfAccountDto extractOfAccountDto);

    @PostMapping("/api/v1/savings/withDrawMoney")
    SavingsAccountDto withDrawMoneySavings(@RequestBody ExtractOfAccountDto extractOfAccountDto);

    @GetMapping("/api/v1/account/process")
    List<ExtractOfAccountDto> getAllProcess();
}
