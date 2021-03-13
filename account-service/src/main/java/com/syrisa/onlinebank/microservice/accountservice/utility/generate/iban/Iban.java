package com.syrisa.onlinebank.microservice.accountservice.utility.generate.iban;

import java.util.function.Function;

public class Iban {

    private Iban() {
    }

    public static final Function<String, String> generateIban = (accountNumber -> "TR30000571" + accountNumber);

}
