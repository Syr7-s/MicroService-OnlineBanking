package com.onlinebank.microservice.cardservice.controller;

import com.onlinebank.microservice.cardservice.dto.CreditCardDto;
import com.onlinebank.microservice.cardservice.entity.impl.CreditCard;
import com.onlinebank.microservice.cardservice.service.abstrct.CreditCardService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/creditCard")
public class CreditCardController {
    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCardDto create(@RequestBody CreditCardDto creditCardDto) {
        try {
            return creditCardService.create(creditCardDto.toCreditCard()).toCreditCardDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @PutMapping
    public CreditCardDto update(@RequestBody CreditCardDto creditCardDto) {
        try {
            return creditCardService.update(creditCardDto.toCreditCard()).toCreditCardDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping("/{cardNumber}")
    public CreditCardDto get(@PathVariable("cardNumber") long cardNumber) {
        try {
            return creditCardService.findCard(cardNumber).toCreditCardDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping("/credit/{userID}")
    public List<CreditCardDto> getCardByUserID(@PathVariable("userID") long userID) {
        try {
            return creditCardService.findCardsByUserID(userID)
                    .stream()
                    .map(CreditCard::toCreditCardDto)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/del/{cardNumber}")
    public String delete(@PathVariable("cardNumber") long cardNumber) {
        try {
            return creditCardService.delete(cardNumber);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping(value = "/cards", params = {"page", "size"})
    public List<CreditCardDto> getCards(@Min(value = 0) int page, @Min(value = 1) int size) {
        return creditCardService.cardPage(PageRequest.of(page, size))
                .stream()
                .map(CreditCard::toCreditCardDto)
                .collect(Collectors.toList());
    }
}
