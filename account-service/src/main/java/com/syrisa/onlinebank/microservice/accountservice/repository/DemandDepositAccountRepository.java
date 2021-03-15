package com.syrisa.onlinebank.microservice.accountservice.repository;


import com.syrisa.onlinebank.microservice.accountservice.entity.impl.DemandDepositAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface DemandDepositAccountRepository extends MongoRepository<DemandDepositAccount, Long> {
    DemandDepositAccount getDemandDepositAccountsByAccountIban(String iban);

    List<DemandDepositAccount> getDemandDepositAccountsByCustomerTC(long customerTC);
}
