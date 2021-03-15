package com.syrisa.onlinebank.microservice.accountservice.service;

import com.syrisa.onlinebank.microservice.accountservice.entity.Entity;

public interface DepositAndWithdrawMoneyService<T extends Entity, V extends Entity> {
    T depositMoneyAccount(V v);

    T withDrawMoneyAccount(V v);
}
