package com.syrisa.onlinebank.microservice.customerservice.repository;

import com.syrisa.onlinebank.microservice.customerservice.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
