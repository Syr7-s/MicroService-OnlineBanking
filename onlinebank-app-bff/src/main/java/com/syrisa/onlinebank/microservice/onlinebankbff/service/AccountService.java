package com.syrisa.onlinebank.microservice.onlinebankbff.service;

public interface AccountService<T> {
    T getAccountByIban(String accountIban);
}
