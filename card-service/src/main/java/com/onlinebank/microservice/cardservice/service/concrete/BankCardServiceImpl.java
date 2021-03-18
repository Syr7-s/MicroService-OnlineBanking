package com.onlinebank.microservice.cardservice.service.concrete;

import com.onlinebank.microservice.cardservice.entity.impl.BankCard;
import com.onlinebank.microservice.cardservice.service.abstrct.BankCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BankCardServiceImpl implements BankCardService {
    @Override
    public BankCard create(BankCard bankCard) {
        return null;
    }

    @Override
    public BankCard update(BankCard bankCard) {
        return null;
    }

    @Override
    public BankCard findCard(long cardNumber) {
        return null;
    }

    @Override
    public String delete(long cardNumber) {
        return null;
    }

    @Override
    public Page<BankCard> cardPage(Pageable pageable) {
        return null;
    }
}
