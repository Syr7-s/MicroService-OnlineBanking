package com.syrisa.onlinebank.microservice.onlinebankbff.service;

import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.DemandDepositAccount;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.Exchange;

public interface DemandDepositAccountService extends AccountService<DemandDepositAccount> {
    DemandDepositAccount transferMoneyBetweenAccounts(Exchange exchange);

    DemandDepositAccount transferMoneyDifferentAccounts(Exchange exchange);
}
