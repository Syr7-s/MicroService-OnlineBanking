package com.syrisa.onlinebank.microservice.onlinebankbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OnlineBankBffApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineBankBffApplication.class, args);
    }

}
