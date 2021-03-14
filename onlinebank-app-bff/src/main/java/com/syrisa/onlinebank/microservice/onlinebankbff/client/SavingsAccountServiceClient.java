package com.syrisa.onlinebank.microservice.onlinebankbff.client;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.SavingsAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("account-service")
public interface SavingsAccountServiceClient {
    @GetMapping("/savings/iban/{accountIban}")
    SavingsAccountDto getDemandDepositAccountByAccountIban(@PathVariable("accountIban") String accountIban);

    @GetMapping("/savings/{customerTC}")
    List<SavingsAccountDto> getDemandDepositAccountByCustomerTC(@PathVariable("customerTC") long customerTC);
}
