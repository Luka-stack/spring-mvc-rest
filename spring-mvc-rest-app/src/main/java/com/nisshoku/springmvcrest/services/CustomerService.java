package com.nisshoku.springmvcrest.services;

import com.nisshoku.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    List<CustomerDTO>  getCustomersByLastName(String lastName);

    CustomerDTO getCustomerById(Long id);

    CustomerDTO creatNewCustomer(CustomerDTO customerDTO);

    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

    CustomerDTO deleteCustomerById(Long id);
}
