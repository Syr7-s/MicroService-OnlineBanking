package com.onlinebank.microservice.cardservice.controller;

import com.onlinebank.microservice.cardservice.dto.BankCardDto;
import com.onlinebank.microservice.cardservice.entity.impl.BankCard;
import com.onlinebank.microservice.cardservice.service.concrete.BankCardServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bankcard")
public class BankCardController {
    private final BankCardServiceImpl bankCardService;

    public BankCardController(BankCardServiceImpl bankCardService) {
        this.bankCardService = bankCardService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankCardDto create(@RequestBody BankCardDto bankCardDto) {
        try {
            return bankCardService.create(bankCardDto.toBankCard()).toBankCardDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @PutMapping
    public BankCardDto update(@RequestBody BankCardDto bankCardDto) {
        try {
            return bankCardService.update(bankCardDto.toBankCard()).toBankCardDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping("/{cardNumber}")
    public BankCardDto get(@PathVariable("cardNumber") long cardNumber) {
        try {
            return bankCardService.findCard(cardNumber).toBankCardDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @DeleteMapping("/del/{cardNumber}")
    public String delete(@PathVariable("cardNumber") long cardNumber) {
        try {
            return bankCardService.delete(cardNumber);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping(value = "/cards", params = {"page", "size"})
    public List<BankCardDto> getCards(@Min(value = 0) int page, @Min(value = 1) int size) {
        return bankCardService.cardPage(PageRequest.of(page, size))
                .stream()
                .map(BankCard::toBankCardDto)
                .collect(Collectors.toList());
    }
}
