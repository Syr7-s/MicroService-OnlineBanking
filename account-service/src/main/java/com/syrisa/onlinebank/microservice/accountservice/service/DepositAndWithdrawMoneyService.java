package com.syrisa.onlinebank.microservice.accountservice.service;

public interface DepositAndWithdrawMoneyService<T, V> {
    T depositMoneyAccount(V v);

    T withDrawMoneyAccount(V v);
}
