package com.demo.project.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.project.entity.Customer;
import com.demo.project.exception.BadRequestException;
import com.demo.project.exception.NotFoundException;
import com.demo.project.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {
    private CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<Customer> getCustomers(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        log.info("{} customers fetched!", customers.getTotalElements());
        return customers;
    }

    public Customer saveCustomer(Customer customer) {
        if(Objects.nonNull(customer.getId())){
            throw new BadRequestException("Customer Id cannot be non-null");
        }
        Customer savedCustomer = customerRepository.save(customer);
        log.info("new customer created with id {}",savedCustomer.getId());
        return savedCustomer;
    }

    public Customer updateCustomer(Customer customer) {
        validateCustomer(customer);
        Customer updatedCustomer =  customerRepository.save(customer);
        log.info("customer updated!", updatedCustomer);
        return customer;
    }

    public ResponseEntity deleteCustomer(Integer customerID) {
        validateCustomerId(customerID);
        log.info("deleting customer id:{}", customerID);
        customerRepository.deleteById(customerID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Customer getCustomer(int customerID) {
        validateCustomerId(customerID);
        Optional<Customer> customer = customerRepository.findById(customerID);
        if(customer.isPresent()){
            log.info("Successfully fetched customer with id: {}", customerID);
            return customer.get();
        }
        throw new NotFoundException("Cannot find customer");
    }
    private static void validateCustomerId(Integer customerID) {
        if(Objects.isNull(customerID)){
            throw new BadRequestException("Id cannot be null");
        }
    }

    private static void validateCustomer(Customer customer) {
        if(Objects.isNull(customer) || Objects.isNull(customer.getId())){
            throw new BadRequestException("Customer id cannot be null");
        }
    }

}
