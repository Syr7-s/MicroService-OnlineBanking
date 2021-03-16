package com.syrisa.onlinebank.microservice.onlinebankbff.service;

import com.syrisa.onlinebank.microservice.onlinebankbff.entity.Entity;

public interface DepositAndWithdrawMoneyService<T extends Entity, V extends Entity> {
    T depositMoneyAccount(V v);

    T withDrawMoneyAccount(V v);
}
