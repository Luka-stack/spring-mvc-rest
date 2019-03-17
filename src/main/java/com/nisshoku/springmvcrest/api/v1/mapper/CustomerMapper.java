package com.nisshoku.springmvcrest.api.v1.mapper;

import com.nisshoku.springmvcrest.api.v1.model.CustomerDTO;
import com.nisshoku.springmvcrest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
