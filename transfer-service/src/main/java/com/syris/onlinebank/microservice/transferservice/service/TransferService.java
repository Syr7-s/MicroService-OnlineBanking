package com.syris.onlinebank.microservice.transferservice.service;

import com.syris.onlinebank.microservice.transferservice.entity.Transfer;
import com.syris.onlinebank.microservice.transferservice.utility.currency.Currency;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

public class TransferService {
    private TransferService() {
    }

    private static final Function<String, Transfer> getConvert = (base -> new RestTemplate()
            .getForObject("https://api.exchangeratesapi.io/latest?base=" + base, Transfer.class)
    );

    public static double convertProcess(Currency currency, Currency convertCurrency, int money) {
        return currency.equals(convertCurrency) ?
                money : money * getConvert.apply(String.valueOf(currency))
                .getRates().get(String.valueOf(convertCurrency));
    }
}
