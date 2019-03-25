package com.nisshoku.springmvcrest.services;

import com.nisshoku.springmvcrest.api.v1.mapper.VendorMapper;
import com.nisshoku.springmvcrest.api.v1.model.VendorDTO;
import com.nisshoku.springmvcrest.controllers.v1.VendorController;
import com.nisshoku.springmvcrest.domain.Vendor;
import com.nisshoku.springmvcrest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> getAllVendors() {

        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {

        return vendorRepository.findById(id)
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {

        return saveAndReturnDTO(vendorMapper.VendorDTOtoVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {

        Vendor vendor = vendorMapper.VendorDTOtoVendor(vendorDTO);
        vendor.setId(id);

        return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {

        return vendorRepository.findById(id).map(vendor -> {

            if (vendorDTO.getName() != null)
                vendor.setName(vendorDTO.getName());

            VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(vendor);
            returnDTO.setVendorUrl(getVendorUrl(vendor.getId()));

            return returnDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

/*    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }*/

    @Override
    public VendorDTO deleteVendorById(Long id) {

        VendorDTO vendorDTO = vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(ResourceNotFoundException::new);
        vendorRepository.deleteById(id);

        return vendorDTO;
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {

        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);

        vendorDTO.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return vendorDTO;
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
}
