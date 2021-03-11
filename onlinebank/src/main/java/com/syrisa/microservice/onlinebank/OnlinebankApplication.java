package com.syrisa.microservice.onlinebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class OnlinebankApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinebankApplication.class, args);
    }

}
