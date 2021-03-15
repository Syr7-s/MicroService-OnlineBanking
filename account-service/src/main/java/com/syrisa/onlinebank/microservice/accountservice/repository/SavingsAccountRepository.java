package com.syrisa.onlinebank.microservice.accountservice.repository;

import com.syrisa.onlinebank.microservice.accountservice.entity.impl.SavingsAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SavingsAccountRepository extends MongoRepository<SavingsAccount, Long> {
    SavingsAccount getSavingsAccountByAccountIban(String iban);

    List<SavingsAccount> getSavingsAccountsByCustomerTC(long customerTC);
}
