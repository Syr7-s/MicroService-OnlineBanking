package com.syrisa.onlinebank.microservice.accountservice.service.abstrct;

import com.syrisa.onlinebank.microservice.accountservice.entity.Entity;

import java.util.List;

public interface ExchangeService<T extends Entity> {
    T createExchange(T t);

    List<T> getAllExchanges();
}
