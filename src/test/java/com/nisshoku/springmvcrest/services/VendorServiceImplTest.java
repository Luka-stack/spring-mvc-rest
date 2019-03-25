package com.nisshoku.springmvcrest.services;

import com.nisshoku.springmvcrest.api.v1.mapper.VendorMapper;
import com.nisshoku.springmvcrest.api.v1.model.VendorDTO;
import com.nisshoku.springmvcrest.controllers.v1.VendorController;
import com.nisshoku.springmvcrest.domain.Vendor;
import com.nisshoku.springmvcrest.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class VendorServiceImplTest {

    private final Long ID = 1l;
    private final String NAME = "Something";

    private VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void getAllVendorsTest() {

        // given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        // when
        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        // then
        assertEquals(2, vendorDTOS.size());

    }

    @Test
    public void getVendorById() {

        // given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        vendor.setId(ID);

        when(vendorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(vendor));

        // when
        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        // then
        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    public void createNewVendorTest() {

        // given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendorSaved = new Vendor();
        vendorSaved.setId(ID);
        vendorSaved.setName(NAME);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendorSaved);

        // when
        VendorDTO savedDTO = vendorService.createNewVendor(vendorDTO);

        // then
        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(VendorController.BASE_URL + "/1", savedDTO.getVendorUrl());
    }

    @Test
    public void saveVendorByDTOTest() {

        // given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(NAME);
        savedVendor.setId(ID);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        // when
        VendorDTO savedDTO = vendorService.saveVendorByDTO(ID, vendorDTO);

        // then

        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(VendorController.BASE_URL + "/1", savedDTO.getVendorUrl());
    }

//    @Test
//    public void deleteVendorById () {
//
//        vendorRepository.deleteById(ID);
//
//        verify(vendorRepository, times(1)).deleteById(anyLong());
//    }
}