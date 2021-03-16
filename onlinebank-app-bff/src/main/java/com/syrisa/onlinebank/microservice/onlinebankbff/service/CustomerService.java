package com.syrisa.onlinebank.microservice.onlinebankbff.service;

import com.syrisa.onlinebank.microservice.onlinebankbff.entity.Entity;

public interface CustomerService<T extends Entity> {
    T get(long tc);
}
