package com.syrisa.onlinebank.microservice.accountservice.service.abstrct;


import com.syrisa.onlinebank.microservice.accountservice.entity.Entity;

import java.util.List;

public interface ExtractOfAccountService<T extends Entity> {
    void create(T t);

    List<T> getAllProcess();
}
