package com.vincent.lab.rest.api.v1.mapper;

import com.vincent.lab.rest.api.v1.model.VendorDTO;
import com.vincent.lab.rest.domain.Vendor;

/**
 * @author Vincent Geng
 *
 * Created on 27 Jan 2018
 */
public interface VendorMapper {
	
	VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOtoVendor(VendorDTO vendorDTO);
    
}