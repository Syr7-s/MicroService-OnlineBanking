package com.syris.onlinebank.microservice.transferservice.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class Transfer {
    private Map<String, Double> rates;
    private String base;
    private LocalDate date;
}
