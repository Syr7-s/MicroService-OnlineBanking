package com.syrisa.onlinebank.microservice.customerservice.repository;

import com.syrisa.onlinebank.microservice.customerservice.entity.impl.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address,Integer> {
}
