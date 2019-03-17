package com.nisshoku.springmvcrest.controllers.v1;

import com.nisshoku.springmvcrest.api.v1.model.CustomerDTO;
import com.nisshoku.springmvcrest.api.v1.model.CustomerListDTO;
import com.nisshoku.springmvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers() {

        return new ResponseEntity<>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("{lastName}")
    public ResponseEntity<CustomerListDTO> getCustomersByLastName(@PathVariable String lastName) {

        return new ResponseEntity<>(
                new CustomerListDTO(customerService.getCustomersByLastName(lastName)), HttpStatus.OK);
    }
}
