package com.nisshoku.springmvcrest.services;

import com.nisshoku.springmvcrest.api.v1.mapper.VendorMapper;
import com.nisshoku.springmvcrest.api.v1.model.VendorDTO;
import com.nisshoku.springmvcrest.bootstrap.Bootstrap;
import com.nisshoku.springmvcrest.domain.Vendor;
import com.nisshoku.springmvcrest.repositories.CategoryRepository;
import com.nisshoku.springmvcrest.repositories.CustomerRepository;
import com.nisshoku.springmvcrest.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VendorServiceImplIT {

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void deleteVendorById() {

        long id = getVendorIdValue();
        Vendor deleteVendor = vendorRepository.getOne(id);
        assertNotNull(deleteVendor);

        List<Vendor> vendors = vendorRepository.findAll();
        VendorDTO vendorDTO = vendorService.deleteVendorById(id);

        assertEquals(deleteVendor.getName(), vendorDTO.getName());
        //assertEquals(vendors.size()-1, vendors.size());
    }

    private Long getVendorIdValue() {
        List<Vendor> vendors = vendorRepository.findAll();

        return vendors.get(0).getId();
    }
}
