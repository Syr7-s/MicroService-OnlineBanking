package com.onlinebank.microservice.cardservice.repository;

import com.onlinebank.microservice.cardservice.entity.impl.BankCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankCardRepository extends CrudRepository<BankCard, Long> {
    Page<BankCard> findAll(Pageable pageable);

    List<BankCard> findBankCardByUserID(long userID);
}
