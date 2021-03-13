package com.syrisa.onlinebank.microservice.accountservice.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class DemandDepositAccount extends Account {

}
