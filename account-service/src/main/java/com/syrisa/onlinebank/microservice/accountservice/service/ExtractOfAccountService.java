package com.syrisa.onlinebank.microservice.accountservice.service;


import java.util.List;

public interface ExtractOfAccountService<T> {
    void create(T t);

    List<T> getAllProcess();
}
