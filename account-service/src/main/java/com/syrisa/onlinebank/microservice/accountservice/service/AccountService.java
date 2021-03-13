package com.syrisa.onlinebank.microservice.accountservice.service;

import com.syrisa.onlinebank.microservice.accountservice.entity.Account;

public interface AccountService<T extends Account> {
    T create(T t);

    T update(T t);

    T get(long accountNumber);

    T getAccountByIBAN(String accountIBAN);

    String delete(long accountNumber);
}
