package com.syrisa.onlinebank.microservice.onlinebankbff.client;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.DemandDepositAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("account-service")
public interface DemandDepositAccountServiceClient {
    @GetMapping("/api/v1/demands/{customerTC}")
    List<DemandDepositAccountDto> getDemandDepositAccountByCustomerTC(@PathVariable("customerTC") long customerTC);

    @GetMapping("/api/v1/demand/iban/{accountIban}")
    DemandDepositAccountDto getDemandDepositAccountByAccountIban(@PathVariable("accountIban") String accountIban);
}
