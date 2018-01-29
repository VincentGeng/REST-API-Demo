package com.vincent.lab.rest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Vincent Geng
 *
 * Created on 27 Jan 2018
 */
public class VendorDTO {
	
	private String name;

    @JsonProperty("vendor_url")
    private String vendorUrl;

	public VendorDTO() {
		
	}

	public VendorDTO(String name, String vendorUrl) {
		super();
		this.name = name;
		this.vendorUrl = vendorUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVendorUrl() {
		return vendorUrl;
	}

	public void setVendorUrl(String vendorUrl) {
		this.vendorUrl = vendorUrl;
	}
    

}
