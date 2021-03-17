package com.syrisa.onlinebank.microservice.onlinebankbff.client;

import com.syrisa.onlinebank.microservice.onlinebankbff.dto.DemandDepositAccountDto;
import com.syrisa.onlinebank.microservice.onlinebankbff.dto.ExchangeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("account-service")
public interface TransferClient {
    @PostMapping("/api/v1/demand/transfer/betweenAccount")
    DemandDepositAccountDto moneyTransferBetweenAccounts(@RequestBody ExchangeDto exchangeDto);

    @PostMapping("/api/v1/demand/transfer/differentAccount")
    DemandDepositAccountDto moneyTransferBetweenDifferentAccounts(@RequestBody ExchangeDto exchangeDto);
}
