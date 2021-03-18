package com.onlinebank.microservice.cardservice.repository;

import com.onlinebank.microservice.cardservice.entity.impl.BankCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCardRepository extends CrudRepository<BankCard,Long> {
}
