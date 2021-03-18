package com.onlinebank.microservice.cardservice.service.concrete;

import com.onlinebank.microservice.cardservice.entity.impl.CreditCard;
import com.onlinebank.microservice.cardservice.service.abstrct.CreditCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {
    @Override
    public CreditCard create(CreditCard creditCard) {
        return null;
    }

    @Override
    public CreditCard update(CreditCard creditCard) {
        return null;
    }

    @Override
    public CreditCard findCard(long cardNumber) {
        return null;
    }

    @Override
    public String delete(long cardNumber) {
        return null;
    }

    @Override
    public Page<CreditCard> cardPage(Pageable pageable) {
        return null;
    }
}
