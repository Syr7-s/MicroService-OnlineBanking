package com.syrisa.onlinebank.microservice.onlinebankbff.service;

import com.syrisa.onlinebank.microservice.onlinebankbff.entity.Entity;

public interface AccountService<T extends Entity> {
    T getAccountByIban(String accountIban);
}
