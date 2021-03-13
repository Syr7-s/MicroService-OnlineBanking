package com.syrisa.onlinebank.microservice.accountservice.service.impl;

import com.syrisa.onlinebank.microservice.accountservice.entity.DemandDepositAccount;
import com.syrisa.onlinebank.microservice.accountservice.service.DemandDepositAccountService;
import org.springframework.stereotype.Service;

@Service
public class DemandDepositAccountServiceImpl implements DemandDepositAccountService {
    @Override
    public DemandDepositAccount create(DemandDepositAccount demandDepositAccount) {
        return null;
    }

    @Override
    public DemandDepositAccount update(DemandDepositAccount demandDepositAccount) {
        return null;
    }

    @Override
    public DemandDepositAccount get(long accountNumber) {
        return null;
    }

    @Override
    public DemandDepositAccount getAccountByIBAN(String accountIBAN) {
        return null;
    }

    @Override
    public String delete(long accountNumber) {
        return null;
    }
}
