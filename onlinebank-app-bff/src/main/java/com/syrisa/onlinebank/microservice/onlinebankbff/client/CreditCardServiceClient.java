package com.syrisa.onlinebank.microservice.onlinebankbff.client;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.CreditCardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient("card-service")
public interface CreditCardServiceClient {
    @GetMapping("/api/v1/creditCard/credit/{userID}")
    List<CreditCardDto> getCardByUserID(@PathVariable("userID") long userID);
}
