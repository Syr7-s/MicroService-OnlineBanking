package com.onlinebank.microservice.cardservice.service.concrete;

import com.onlinebank.microservice.cardservice.entity.impl.CreditCard;
import com.onlinebank.microservice.cardservice.repository.CreditCardRepository;
import com.onlinebank.microservice.cardservice.service.abstrct.CreditCardService;
import com.onlinebank.microservice.cardservice.utility.generate.cardaccount.CardAccountNumber;
import com.onlinebank.microservice.cardservice.utility.generate.securitycode.SecurityCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public CreditCard create(CreditCard creditCard) {
        creditCard.setCardAccountNumber(CardAccountNumber.generateCardAccountNumber.get());
        creditCard.setSecurityCode(SecurityCode.securityCode.get());
        return creditCardRepository.save(creditCard);
    }

    @Override
    public CreditCard update(CreditCard creditCard) {
        try {
            if (findCard(creditCard.getCardAccountNumber()) != null) {
                return creditCardRepository.save(creditCard);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Card account not found.");
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public CreditCard findCard(long cardNumber) {
        return creditCardRepository.findById(cardNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found."));
    }

    @Override
    public List<CreditCard> findCardsByUserID(long userID) {
        return creditCardRepository.getCreditCardsByUserID(userID);
    }

    @Override
    public String delete(long cardNumber) {
        CreditCard creditCard = findCard(cardNumber);
        creditCardRepository.delete(creditCard);
        return cardNumber + " number card was deleted.";
    }

    @Override
    public Page<CreditCard> cardPage(Pageable pageable) {
        return creditCardRepository.findAll(pageable);
    }
}
