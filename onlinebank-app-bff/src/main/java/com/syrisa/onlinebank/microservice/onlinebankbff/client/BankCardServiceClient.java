package com.syrisa.onlinebank.microservice.onlinebankbff.client;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.BankCardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("card-service")
public interface BankCardServiceClient {
    @GetMapping("/api/v1/bankcard/{cardNumber}")
    BankCardDto get(@PathVariable("cardNumber") long cardNumber);
}
