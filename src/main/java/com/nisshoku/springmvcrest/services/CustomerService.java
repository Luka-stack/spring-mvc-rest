package com.nisshoku.springmvcrest.services;

import com.nisshoku.springmvcrest.api.v1.model.CustomerDTO;
import com.nisshoku.springmvcrest.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    List<CustomerDTO>  getCustomersByLastName(String lastName);

    CustomerDTO getCustomerById(Long id);

    CustomerDTO creatNewCustomer(CustomerDTO customerDTO);

    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

    CustomerDTO deleteCustomerById(Long id);
}
