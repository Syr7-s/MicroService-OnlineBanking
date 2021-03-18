package com.onlinebank.microservice.cardservice.service.concrete;

import com.onlinebank.microservice.cardservice.entity.impl.BankCard;
import com.onlinebank.microservice.cardservice.repository.BankCardRepository;
import com.onlinebank.microservice.cardservice.service.abstrct.BankCardService;
import com.onlinebank.microservice.cardservice.utility.generate.cardaccount.CardAccountNumber;
import com.onlinebank.microservice.cardservice.utility.generate.securitycode.SecurityCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Service
public class BankCardServiceImpl implements BankCardService {
    private final BankCardRepository bankCardRepository;

    public BankCardServiceImpl(BankCardRepository bankCardRepository) {
        this.bankCardRepository = bankCardRepository;
    }

    @Override
    public BankCard create(BankCard bankCard) {
        try {
            bankCard.setBankCardAccountNumber(CardAccountNumber.generateCardAccountNumber.get());
            bankCard.setBankCardSecurityCode(SecurityCode.securityCode.get());
            return bankCardRepository.save(bankCard);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Card must not created.");
        }
    }

    @Override
    public BankCard update(BankCard bankCard) {
        try {
            if (findCard(bankCard.getBankCardAccountNumber()) != null) {
                return bankCardRepository.save(bankCard);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Card not update");
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public BankCard findCard(long cardNumber) {
        return bankCardRepository.findById(cardNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank Card not found."));
    }

    @Override
    public String delete(long cardNumber) {
        BankCard bankCard = findCard(cardNumber);
        bankCardRepository.delete(bankCard);
        return bankCard.getBankCardAccountNumber() + " number account was deleted.";
    }

    @Override
    public Page<BankCard> cardPage(Pageable pageable) {
        return bankCardRepository.findAll(pageable);
    }
}
