package com.syrisa.onlinebank.microservice.customerservice.service.concrete;

import com.syrisa.onlinebank.microservice.customerservice.entity.impl.Customer;
import com.syrisa.onlinebank.microservice.customerservice.repository.CustomerRepository;
import com.syrisa.onlinebank.microservice.customerservice.service.impl.CustomerServiceImpl;
import com.syrisa.onlinebank.microservice.customerservice.utility.enums.Gender;
import com.syrisa.onlinebank.microservice.customerservice.utility.generate.tc.TC;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerServiceImplTest {
    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;
    static Customer customer = new Customer();

    @BeforeAll
    static void setUp() {
        customer.setCustomerTC(TC.generateTC.get());
        customer.setCustomerName("Ali");
        customer.setCustomerLastname("Demir");
        customer.setCustomerGender(Gender.MALE);
        customer.setCustomerPhone("5359874512");
        customer.setCustomerEmail("alidemir7@gmail.com");
        customer.setCustomerBirthDate(LocalDate.of(1993, 4, 25));
    }

    @BeforeEach
    void create() {
        customerService = new CustomerServiceImpl(customerRepository,null);
        customerRepository.save(customer);
    }

    @Test
    void get() {
        Mockito.when(customerRepository.findById(customer.getCustomerTC())).thenReturn(java.util.Optional.ofNullable(customer));
        Assertions.assertNotEquals("Isa", customerService.get(customer.getCustomerTC()).getCustomerName());
    }

}