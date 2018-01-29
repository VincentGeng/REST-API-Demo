package com.vincent.lab.rest.service;

import com.vincent.lab.rest.api.v1.model.VendorDTO;
import com.vincent.lab.rest.api.v1.model.VendorListDTO;

/**
 * @author Vincent Geng
 *
 * Created on 27 Jan 2018
 */
public interface VendorService {

    VendorDTO getVendorById(Long id);

    VendorListDTO getAllVendors();

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
    
}
