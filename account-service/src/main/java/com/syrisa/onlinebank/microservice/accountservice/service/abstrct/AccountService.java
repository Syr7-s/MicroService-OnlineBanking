package com.syrisa.onlinebank.microservice.accountservice.service.abstrct;


import com.syrisa.onlinebank.microservice.accountservice.entity.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface AccountService<T extends Entity> {
    T create(T t);

    T update(T t);

    T get(long accountNumber);

    T getAccountByIBAN(String accountIBAN);

    Page<T> getAccounts(Pageable pageable);

    List<T> getAccountByCustomers(long customerTC);

    String delete(long accountNumber);
}
