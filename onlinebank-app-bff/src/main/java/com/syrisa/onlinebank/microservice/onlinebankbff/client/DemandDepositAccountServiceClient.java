package com.syrisa.onlinebank.microservice.onlinebankbff.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("account-service")
public interface DemandDepositAccountServiceClient {
}
