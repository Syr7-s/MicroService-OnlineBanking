package com.syrisa.onlinebank.microservice.onlinebankbff.client;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.DemandDepositAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("account-service")
public interface DemandDepositAccountServiceClient {
    @GetMapping("/demands/{customerTC}")
    List<DemandDepositAccountDto> getDemandDepositAccountByCustomerTC(@PathVariable("customerTC") long customerTC);

    @GetMapping("/demand/iban/{accountIban}")
    DemandDepositAccountDto getDemandDepositAccountByAccountIban(@PathVariable("accountIban") String accountIban);
}
