package com.nisshoku.springmvcrest.controllers.v1;

import com.nisshoku.springmvcrest.api.v1.model.CustomerDTO;
import com.nisshoku.springmvcrest.services.CustomerService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    private static final Long ID = 1L;
    private static final String FIRST_NAME = "Takahiro";
    private static final String LAST_NAME = "Hayami";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCustomers() throws Exception {

        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(ID);
        customer1.setFirstName(FIRST_NAME);
        customer1.setLastName(LAST_NAME);

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setId(2L);
        customer2.setFirstName("Ryuki");
        customer2.setLastName("Rafa");

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetCustomersByLastName() throws Exception {

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setId(ID);
        customerDTO1.setFirstName(FIRST_NAME);
        customerDTO1.setLastName(LAST_NAME);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setId(2L);
        customerDTO2.setFirstName("Luki");
        customerDTO2.setLastName(LAST_NAME);

        List<CustomerDTO> customerDTOS = Arrays.asList(customerDTO1, customerDTO2);

        when(customerService.getCustomersByLastName(anyString())).thenReturn(customerDTOS);

        mockMvc.perform(get("/api/v1/customers/" + LAST_NAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
                //.andExpect(jsonPath("$.customers[:1].lastName", equalTo(LAST_NAME)));

    }
}