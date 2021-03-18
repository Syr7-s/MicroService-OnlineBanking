package com.onlinebank.microservice.cardservice.utility.generate.securitycode;

import java.util.Random;
import java.util.function.Supplier;

public final class SecurityCode {
    private SecurityCode() {
    }

    public static final Supplier<String> securityCode = () -> {
        StringBuilder securityCodeGenerate = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            securityCodeGenerate.append(new Random().nextInt(10));
        }
        return securityCodeGenerate.toString();
    };
}