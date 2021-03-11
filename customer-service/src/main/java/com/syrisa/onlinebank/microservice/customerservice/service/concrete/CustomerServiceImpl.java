package com.syrisa.onlinebank.microservice.customerservice.service.concrete;


import com.syrisa.onlinebank.microservice.customerservice.entity.Customer;
import com.syrisa.onlinebank.microservice.customerservice.repository.AddressRepository;
import com.syrisa.onlinebank.microservice.customerservice.repository.CustomerRepository;
import com.syrisa.onlinebank.microservice.customerservice.service.abstrct.CustomerService;
import com.syrisa.onlinebank.microservice.customerservice.utility.generate.tc.TC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
