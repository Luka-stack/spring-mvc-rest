package com.nisshoku.springmvcrest.controllers.v1;

import com.nisshoku.springmvcrest.api.v1.model.CustomerDTO;
import com.nisshoku.springmvcrest.controllers.RestResponseEntityExceptionHandler;
import com.nisshoku.springmvcrest.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.nisshoku.springmvcrest.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    public void testListCustomers() throws Exception {

        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName(FIRST_NAME);
        customer1.setLastName(LAST_NAME);

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("Ryuki");
        customer2.setLastName("Rafa");

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetCustomersByLastName() throws Exception {

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(FIRST_NAME);
        customerDTO1.setLastName(LAST_NAME);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setFirstName("Luki");
        customerDTO2.setLastName(LAST_NAME);

        List<CustomerDTO> customerDTOS = Arrays.asList(customerDTO1, customerDTO2);

        when(customerService.getCustomersByLastName(LAST_NAME)).thenReturn(customerDTOS);

        mockMvc.perform(get(CustomerController.BASE_URL +  "/bylastname/" + LAST_NAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
                //.andExpect(jsonPath("$.customers[:1].lastName", equalTo(LAST_NAME)));

    }

    @Test
    public void testGetCustomerById() throws Exception {

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(FIRST_NAME);
        customerDTO1.setLastName(LAST_NAME);
        customerDTO1.setCustomerUrl(CustomerController.BASE_URL +  "/1");

        when(customerService.getCustomerById(ID)).thenReturn(customerDTO1);

        mockMvc.perform(get(CustomerController.BASE_URL +  "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

    }

    @Test
    public void creatNewCustomerTest() throws Exception{

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        CustomerDTO returnedDTO = new CustomerDTO();
        returnedDTO.setFirstName(customerDTO.getFirstName());
        returnedDTO.setLastName(customerDTO.getLastName());
        returnedDTO.setCustomerUrl(CustomerController.BASE_URL +  "/1");

        when(customerService.creatNewCustomer(customerDTO)).thenReturn(returnedDTO);

        mockMvc.perform(post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL +  "/1")));
    }

    @Test
    public void updateCustomerTest() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(FIRST_NAME);
        returnDTO.setLastName(LAST_NAME);
        returnDTO.setCustomerUrl(CustomerController.BASE_URL +  "/1");

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put(CustomerController.BASE_URL +  "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL +  "/1")));
    }

    @Test
    public void patchCustomerTest() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(FIRST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(FIRST_NAME);
        returnDTO.setLastName(LAST_NAME);
        returnDTO.setCustomerUrl(CustomerController.BASE_URL +  "/1");

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(CustomerController.BASE_URL +  "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL +  "/1")));
    }

    @Test
    public void deleteCustomerTest() throws Exception{

        mockMvc.perform(delete(CustomerController.BASE_URL +  "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }
}