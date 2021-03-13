package com.syrisa.onlinebank.microservice.accountservice.service;


import java.util.List;
public interface AccountService<T> {
    T create(T t);

    T update(T t);

    T get(long accountNumber);

    T getAccountByIBAN(String accountIBAN);

    List<T> getAccountByCustomers(long customerTC);

    String delete(long accountNumber);
}
