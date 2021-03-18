package com.onlinebank.microservice.cardservice.repository;

import com.onlinebank.microservice.cardservice.entity.impl.CreditCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard,Long> {
}
