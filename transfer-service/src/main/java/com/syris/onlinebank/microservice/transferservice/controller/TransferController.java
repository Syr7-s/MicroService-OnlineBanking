package com.syris.onlinebank.microservice.transferservice.controller;

import com.syris.onlinebank.microservice.transferservice.entity.Transfer;
import com.syris.onlinebank.microservice.transferservice.service.TransferService;
import com.syris.onlinebank.microservice.transferservice.utility.currency.Currency;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1")
public class TransferController {

    @GetMapping("/transfer")
    public Transfer get(@RequestParam("base") Currency base) {
        try {
            return new RestTemplate()
                    .getForObject("https://api.exchangeratesapi.io/latest?base=" + base, Transfer.class);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Currency is invalid.");
        }
    }

    @GetMapping("/exchange/{currency}/transfer/{convertCurrency}")
    public double getConvertMoney(@NotNull @PathVariable("currency") Currency currency,
                                  @NotNull @PathVariable("convertCurrency") Currency convertCurrency,
                                  @RequestParam("money") int money) {
        return TransferService.convertProcess(currency, convertCurrency, money);
    }
}