package com.nisshoku.springmvcrest.services;

import com.nisshoku.springmvcrest.api.v1.mapper.CustomerMapper;
import com.nisshoku.springmvcrest.api.v1.model.CustomerDTO;
import com.nisshoku.springmvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getCustomersByLastName(String lastName) {

        return customerRepository.findByLastName(lastName)
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }
}
