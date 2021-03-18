package com.syrisa.onlinebank.microservice.accountservice.service.concrete;


import com.syrisa.onlinebank.microservice.accountservice.entity.impl.Exchange;
import com.syrisa.onlinebank.microservice.accountservice.repository.ExchangeRepository;
import com.syrisa.onlinebank.microservice.accountservice.service.abstrct.ExchangeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class ExchangeServiceImpl implements ExchangeService<Exchange> {
    private final ExchangeRepository exchangeRepository;

    public ExchangeServiceImpl(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public Exchange createExchange(Exchange exchange) {
        try {
            exchange.setProcessNumber(UUID.randomUUID());
            exchange.setTime(LocalTime.now());
            exchange.setDate(LocalDate.now());
            return exchangeRepository.save(exchange);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public List<Exchange> getAllExchanges() {
        try {
            return exchangeRepository.findAll();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
