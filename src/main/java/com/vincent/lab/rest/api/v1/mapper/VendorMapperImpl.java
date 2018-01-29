package com.vincent.lab.rest.api.v1.mapper;

import org.springframework.stereotype.Component;

import com.vincent.lab.rest.api.v1.model.VendorDTO;
import com.vincent.lab.rest.domain.Vendor;


/**
 * @author Vincent Geng
 *
 * Created on 27 Jan 2018
 */
@Component
public class VendorMapperImpl implements VendorMapper {

	public VendorDTO vendorToVendorDTO(Vendor vendor) {
		if(vendor == null) {
			return null;
		}
		
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName(vendor.getName());
		
		return vendorDTO;
	}

	public Vendor vendorDTOtoVendor(VendorDTO vendorDTO) {
		if(vendorDTO == null) {
			return null;
		}
		
		Vendor vendor = new Vendor();
		vendor.setName(vendorDTO.getName());
		
		return vendor;
	}

}

