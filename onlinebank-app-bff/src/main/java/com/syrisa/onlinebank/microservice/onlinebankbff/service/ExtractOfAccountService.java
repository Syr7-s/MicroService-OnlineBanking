package com.syrisa.onlinebank.microservice.onlinebankbff.service;

import com.syrisa.onlinebank.microservice.onlinebankbff.entity.Entity;

import java.util.List;

public interface ExtractOfAccountService<T extends Entity> {
    List<T> getAllProcess();
}
