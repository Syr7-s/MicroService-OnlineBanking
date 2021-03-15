package com.syrisa.onlinebank.microservice.accountservice.service;


import com.syrisa.onlinebank.microservice.accountservice.entity.Entity;

import java.util.List;
public interface AccountService<T extends Entity> {
    T create(T t);

    T update(T t);

    T get(long accountNumber);

    T getAccountByIBAN(String accountIBAN);

    List<T> getAccountByCustomers(long customerTC);

    String delete(long accountNumber);
}
