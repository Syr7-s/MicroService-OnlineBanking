package com.syrisa.onlinebank.microservice.onlinebankbff.service;

public interface CustomerService<T> {
    T get(long tc);
}
