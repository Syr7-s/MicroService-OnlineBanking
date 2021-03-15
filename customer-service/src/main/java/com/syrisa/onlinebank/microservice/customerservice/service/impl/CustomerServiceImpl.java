package com.syrisa.onlinebank.microservice.customerservice.service.impl;


import com.syrisa.onlinebank.microservice.customerservice.entity.impl.Customer;
import com.syrisa.onlinebank.microservice.customerservice.repository.AddressRepository;
import com.syrisa.onlinebank.microservice.customerservice.repository.CustomerRepository;
import com.syrisa.onlinebank.microservice.customerservice.service.CustomerService;
import com.syrisa.onlinebank.microservice.customerservice.utility.generate.tc.TC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService<Customer> {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Customer create(Customer customer) {
        try {
            customer.setCustomerTC(TC.generateTC.get());
            addressRepository.save(customer.getAddress());
            return customerRepository.save(customer);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public Customer get(long tc) {
        return customerRepository.findById(tc)
                .orElseThrow(() -> (new ResponseStatusException(HttpStatus.NOT_FOUND, tc + " number TC was not found")));
    }

    @Override
    public Customer update(Customer customer) {
        Customer updatedCustomer = get(customer.getCustomerTC());
        updatedCustomer.setCustomerName(customer.getCustomerName());
        updatedCustomer.setCustomerLastname(customer.getCustomerLastname());
        updatedCustomer.setCustomerGender(customer.getCustomerGender());
        updatedCustomer.setCustomerPhone(customer.getCustomerPhone());
        updatedCustomer.setCustomerEmail(customer.getCustomerEmail());
        updatedCustomer.setCustomerBirthDate(customer.getCustomerBirthDate());
        updatedCustomer.setState(customer.isState());
        updatedCustomer.setAddress(customer.getAddress());
        return customerRepository.save(updatedCustomer);
    }

    @Override
    public String delete(long tc) {
        Customer customer = get(tc);
        customerRepository.delete(customer);
        return tc + " number TC was deleted";
    }

    @Override
    public Page<Customer> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
