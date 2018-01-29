package com.vincent.lab.rest.api.v1.mapper;

import com.vincent.lab.rest.api.v1.model.VendorDTO;
import com.vincent.lab.rest.domain.Vendor;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 * @author Vincent Geng
 *
 * Created on 28 Jan 2018
 */
public class VendorMapperTest {
	
	public static final String NAME = "someName";
	
	VendorMapper vendorMapper;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.vendorMapper = new VendorMapperImpl();
	}

	/**
	 * Test method for {@link com.vincent.lab.rest.api.v1.mapper.VendorMapperImpl#vendorToVendorDTO(com.vincent.lab.rest.domain.Vendor)}.
	 */
	@Test
	public void testVendorToVendorDTO() {
		
	}

	/**
	 * Test method for {@link com.vincent.lab.rest.api.v1.mapper.VendorMapperImpl#vendorDTOtoVendor(com.vincent.lab.rest.api.v1.model.VendorDTO)}.
	 */
	@Test
	public void testVendorDTOtoVendor() {
		
	}

}
