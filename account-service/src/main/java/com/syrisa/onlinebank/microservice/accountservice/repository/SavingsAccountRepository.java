package com.syrisa.onlinebank.microservice.accountservice.repository;

import com.syrisa.onlinebank.microservice.accountservice.entity.SavingsAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsAccountRepository extends MongoRepository<SavingsAccount, Long> {
    SavingsAccount getSavingsAccountByAccountIban(String iban);
}
