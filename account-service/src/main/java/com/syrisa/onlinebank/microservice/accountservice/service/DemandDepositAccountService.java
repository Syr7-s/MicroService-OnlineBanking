package com.syrisa.onlinebank.microservice.accountservice.service;

import com.syrisa.onlinebank.microservice.accountservice.entity.impl.DemandDepositAccount;
import com.syrisa.onlinebank.microservice.accountservice.entity.impl.Exchange;

public interface DemandDepositAccountService extends AccountService<DemandDepositAccount> {
    DemandDepositAccount transferMoneyBetweenAccounts(Exchange exchange);

    DemandDepositAccount transferMoneyDifferentAccounts(Exchange exchange);
}
