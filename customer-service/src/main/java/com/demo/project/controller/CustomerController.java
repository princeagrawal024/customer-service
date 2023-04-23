package com.demo.project.controller;

import static com.demo.project.controller.CustomerController.URL_PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.entity.Customer;
import com.demo.project.service.CustomerService;
@RestController
@RequestMapping(path = URL_PREFIX)
public class CustomerController {
    public static final String URL_PREFIX = "/api/v1/customers";
    private  CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Page<Customer>> getCustomers(Pageable pageable){
        Page<Customer> customers = customerService.getCustomers(pageable);
        return  new ResponseEntity(customers,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") int id){
        Customer customer = customerService.getCustomer(id);
        return new ResponseEntity(customer,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer saveCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity(saveCustomer,HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        Customer theCustomer = customerService.updateCustomer(customer);
        ResponseEntity response = new ResponseEntity(theCustomer,HttpStatus.OK);
        return  response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Integer id){
        customerService.deleteCustomer(id);
        String message = new String("Customer Deleted with id:"+id);
        ResponseEntity response = new ResponseEntity(message,HttpStatus.OK);
        return response;
    }
}
