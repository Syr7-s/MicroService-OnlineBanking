package com.syrisa.onlinebank.microservice.customerservice.service.concrete;


import com.syrisa.onlinebank.microservice.customerservice.entity.Customer;
import com.syrisa.onlinebank.microservice.customerservice.repository.CustomerRepository;
import com.syrisa.onlinebank.microservice.customerservice.service.abstrct.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService<Customer> {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer) {
        return null;
    }

    @Override
    public Customer get(long tc) {
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public String delete(long tc) {
        return null;
    }

    @Override
    public Page<Customer> getCustomers(Pageable pageable) {
        return null;
    }
}
