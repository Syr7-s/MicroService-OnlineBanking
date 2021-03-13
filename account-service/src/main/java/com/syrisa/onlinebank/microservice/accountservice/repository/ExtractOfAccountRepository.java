package com.syrisa.onlinebank.microservice.accountservice.repository;


import com.syrisa.onlinebank.microservice.accountservice.entity.ExtractOfAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractOfAccountRepository extends MongoRepository<ExtractOfAccount, Long> {
}
