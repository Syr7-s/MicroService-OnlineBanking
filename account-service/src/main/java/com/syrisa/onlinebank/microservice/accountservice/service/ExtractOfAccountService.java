package com.syrisa.onlinebank.microservice.accountservice.service;

import com.syrisa.onlinebank.microservice.accountservice.entity.DemandDepositAccount;
import com.syrisa.onlinebank.microservice.accountservice.entity.ExtractOfAccount;
import com.syrisa.onlinebank.microservice.accountservice.entity.SavingsAccount;

public interface ExtractOfAccountService {
    DemandDepositAccount depositMoneyDemandDepositAccount(ExtractOfAccount extractOfAccount);
    DemandDepositAccount withDrawMoneyDemandDepositAccount(ExtractOfAccount extractOfAccount);

    SavingsAccount depositMoneySavingsAccount(ExtractOfAccount extractOfAccount);
    SavingsAccount withDrawMoneySavingsAccount(ExtractOfAccount extractOfAccount);
}
