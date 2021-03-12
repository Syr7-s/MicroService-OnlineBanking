package com.syrisa.onlinebank.microservice.customerservice.controller;

import com.syrisa.onlinebank.microservice.customerservice.dto.CustomerDto;
import com.syrisa.onlinebank.microservice.customerservice.entity.Customer;
import com.syrisa.onlinebank.microservice.customerservice.service.abstrct.CustomerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    private final CustomerService<Customer> customerService;

    public CustomerController(CustomerService<Customer> customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> create(@RequestBody CustomerDto customerDto) {
        try {
            CustomerDto editedCustomer = customerService.create(customerDto.toCustomer()).toCustomerDto();
            URI location = ServletUriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/v1/customer")
                    .path("/{customerTC}")
                    .buildAndExpand(editedCustomer.toCustomer().getCustomerTC())
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        }
    }

    @GetMapping("/customer/{customerTC}")
    public CustomerDto get(@PathVariable("customerTC") long customerTC) {
        return customerService.get(customerTC).toCustomerDto();
    }

    @GetMapping(value = "/customers", params = {"page", "size"})
    public List<CustomerDto> getCustomers(@Min(value = 0) @RequestParam("page") int page, @Min(value = 1) @RequestParam("size") int size) {
        return customerService.getCustomers(PageRequest.of(page, size)).stream()
                .map(Customer::toCustomerDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/customer/update")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto update(@RequestBody CustomerDto customerDto) {
        try {
            return customerService.update(customerDto.toCustomer()).toCustomerDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @DeleteMapping("/customer/{customerTC}")
    public String delete(@PathVariable("customerTC") long customerTC) {
        return customerService.delete(customerTC);
    }
}
