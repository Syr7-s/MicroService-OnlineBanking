package com.syrisa.onlinebank.microservice.accountservice.repository;


import com.syrisa.onlinebank.microservice.accountservice.entity.DemandDepositAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandDepositAccountRepository extends MongoRepository<DemandDepositAccount, Long> {
    DemandDepositAccount getDemandDepositAccountsByAccountIban(String iban);
}
