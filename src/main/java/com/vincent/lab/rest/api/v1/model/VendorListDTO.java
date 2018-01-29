package com.vincent.lab.rest.api.v1.model;

import java.util.List;

/**
 * @author Vincent Geng
 *
 * Created on 27 Jan 2018
 */
public class VendorListDTO {
	
	List<VendorDTO> vendors;
	
	public VendorListDTO() {
		
	}

	public VendorListDTO(List<VendorDTO> vendors) {
		super();
		this.vendors = vendors;
	}

	public List<VendorDTO> getVendors() {
		return vendors;
	}

	public void setVendors(List<VendorDTO> vendors) {
		this.vendors = vendors;
	}
	
}
