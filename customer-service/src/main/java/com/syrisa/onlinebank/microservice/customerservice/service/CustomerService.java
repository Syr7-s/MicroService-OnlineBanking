package com.syrisa.onlinebank.microservice.customerservice.service;

import com.syrisa.onlinebank.microservice.customerservice.entity.Model;
import com.syrisa.onlinebank.microservice.customerservice.entity.impl.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService<T extends Model> {
    Customer create(T t);

    T get(long tc);

    T update(T t);

    String delete(long tc);

    Page<T> getCustomers(Pageable pageable);
}
