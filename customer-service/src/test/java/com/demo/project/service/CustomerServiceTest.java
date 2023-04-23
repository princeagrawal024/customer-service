package com.demo.project.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.project.entity.Customer;
import com.demo.project.exception.BadRequestException;
import com.demo.project.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Mock
    Pageable pageable;

    @Test
    @DisplayName("fetch customer by ID")
    void getCustomer(){
        Customer customerExpected = new Customer(101,"firstName", "LastName", "Address", LocalDate.now(),String.valueOf("4545454549L") );
        when(customerRepository.findById(101)).thenReturn(Optional.ofNullable(customerExpected));
        Customer customerActual = customerService.getCustomer(101);
        assertEquals(customerExpected.getId(),customerActual.getId());
    }

    @Test
    @DisplayName("Save a valid customer")
    void saveCustomer(){
        LocalDate date = LocalDate.now();
        Customer customer = new Customer(null,"firstName", "LastName", "Address", date,String.valueOf("4545454549L" ));
        Customer savedCustomer =  new Customer(101,"firstName", "LastName", "Address", date,String.valueOf("4545454549L") );
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        Customer customersActual = customerService.saveCustomer(customer);
        assertEquals(savedCustomer.getId(),customersActual.getId());
    }

    @Test
    @DisplayName("Update a valid customer")
    void updateCustomer(){
        Customer customerExpected = new Customer(101,"firstName", "LastName", "Address", LocalDate.now(),String.valueOf("4545454549L") );
        when(customerRepository.save(customerExpected)).thenReturn(customerExpected);
        Customer customersActual = customerService.updateCustomer(customerExpected);

        assertAll(
                ()->assertEquals(customerExpected.getId(),customersActual.getId()),
                ()->assertEquals(customerExpected.getFirstName(),customersActual.getFirstName()),
                ()->assertEquals(customerExpected.getAddress(),customersActual.getAddress())
        );
    }

    @Test
    @DisplayName("Delete a valid customer")
    void deleteCustomer(){
        ResponseEntity response = new ResponseEntity(HttpStatus.OK);
        assertEquals(customerService.deleteCustomer(100), response);
    }

    @Test
    @DisplayName("Save an Invalid customer")
    void saveCustomerWithNonNullId(){

        Customer customer =  new Customer(101,"firstName", "LastName", "Address", LocalDate.now(),String.valueOf("4545454549L") );
        when(customerRepository.save(customer)).thenReturn(customer);
        assertThrows(BadRequestException.class,()->customerService.saveCustomer(customer));
    }

    @Test
    @DisplayName("Update an Invalid customer")
    void updateCustomerWithoutId(){
        Customer customer =  new Customer(null,"firstName", "LastName", "Address", LocalDate.now(),String.valueOf("4545454549L") );
        when(customerRepository.save(customer)).thenReturn(customer);
        assertThrows(BadRequestException.class,()->customerService.updateCustomer(customer));
    }

}
