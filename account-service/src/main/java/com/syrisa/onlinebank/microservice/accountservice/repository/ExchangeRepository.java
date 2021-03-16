package com.syrisa.onlinebank.microservice.accountservice.repository;

import com.syrisa.onlinebank.microservice.accountservice.entity.impl.Exchange;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends MongoRepository<Exchange,Long> {

}
