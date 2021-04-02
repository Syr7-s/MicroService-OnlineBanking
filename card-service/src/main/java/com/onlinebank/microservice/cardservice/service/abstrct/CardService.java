package com.onlinebank.microservice.cardservice.service.abstrct;

import com.onlinebank.microservice.cardservice.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService<T extends Card> {
    T create(T t);

    T update(T t);

    T findCard(long cardNumber);

    T findCardByUserID(long userID);

    String delete(long cardNumber);

    Page<T> cardPage(Pageable pageable);


}
