package com.onlinebank.microservice.cardservice.utility.generate.cardaccount;


import java.util.Random;
import java.util.function.Supplier;

public final class CardAccountNumber {
    private CardAccountNumber() {
    }

    public static final Supplier<Long> generateCardAccountNumber = () -> {
        StringBuilder cardAccountNumber = new StringBuilder();
        cardAccountNumber.append("85");
        for (int i = 0; i < 14; i++) {
            cardAccountNumber.append(new Random().nextInt(10));
        }
        return Long.parseLong(cardAccountNumber.toString());
    };
}