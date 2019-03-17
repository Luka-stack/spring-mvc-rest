package com.nisshoku.springmvcrest.services;

import com.nisshoku.springmvcrest.api.v1.mapper.CustomerMapper;
import com.nisshoku.springmvcrest.api.v1.model.CustomerDTO;
import com.nisshoku.springmvcrest.domain.Customer;
import com.nisshoku.springmvcrest.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class CustomerServiceTest {

    private final Long ID = 1L;
    private final String FIRST_NAME = "Taka";
    private final String LAST_NAME = "Hayami";
    private CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCustomersTest() {

        // given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        // when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        // then
        assertEquals(3, customerDTOS.size());

    }

    @Test
    public void getCustomersByLastNameTest() {

        // given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Ryuki");
        customer2.setLastName(LAST_NAME);

        List<Customer> customers = Arrays.asList(customer, customer2);

        when(customerRepository.findByLastName(anyString())).thenReturn(customers);

        // when
        List<CustomerDTO> customerDTOS = customerService.getCustomersByLastName(LAST_NAME);

        // then
        assertEquals(2, customerDTOS.size());
        assertEquals(LAST_NAME, customerDTOS.get(0).getLastName());
        assertEquals(LAST_NAME, customerDTOS.get(1).getLastName());
    }
}
