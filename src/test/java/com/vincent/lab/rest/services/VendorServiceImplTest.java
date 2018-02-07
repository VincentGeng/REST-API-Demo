/**
 * 
 */
package com.vincent.lab.rest.services;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vincent.lab.rest.api.v1.mapper.VendorMapper;
import com.vincent.lab.rest.api.v1.mapper.VendorMapperImpl;
import com.vincent.lab.rest.api.v1.model.VendorDTO;
import com.vincent.lab.rest.api.v1.model.VendorListDTO;
import com.vincent.lab.rest.domain.Vendor;
import com.vincent.lab.rest.repositories.VendorRepository;
import com.vincent.lab.rest.service.ResourceNotFoundException;
import com.vincent.lab.rest.service.VendorService;
import com.vincent.lab.rest.service.VendorServiceImpl;

/**
 * @author Vincent Geng
 *
 * Created on 6 Feb 2018
 */
public class VendorServiceImplTest {
	
	public static final String NAME_1 = "My First Vendor";
	public static final long ID_1 = 1L;
	public static final String NAME_2 = "My Second Vendor";
	public static final long ID_2 = 2L;
	
	@Mock
	VendorRepository vendorRepository;
	
	VendorMapper vendorMapper;
	VendorService vendorService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		vendorMapper = new VendorMapperImpl();
		vendorService = new VendorServiceImpl(vendorMapper, vendorRepository);
		
	}

	@Test
	public void getVendorById() throws Exception {
		//given
		Vendor vendor = getVendor1();
		
		//mokito BBD syntax
		given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
		
		//when
		VendorDTO vendorDTO = vendorService.getVendorById(1L);
		
		//then
		then(vendorRepository).should(times(1)).findById(anyLong());
		
		//JUnit Assert that with matchers
		assertThat(vendorDTO.getName(), is(equalTo(NAME_1)));
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void getVendorByIdNotFound() throws Exception {
		//given
		
		//mokito BBD syntax since mockto 1.10.0
		given(vendorRepository.findById(anyLong())).willReturn(Optional.empty());
		
		//when
		vendorService.getVendorById(1L);
		
		//then
		then(vendorRepository).should(times(1)).findById(anyLong());
	}
	
	@Test
	public void getAllVendors() throws Exception {
		//given
		List<Vendor> vendors = Arrays.asList(getVendor1(), getVendor2());
		
		//mokito BBD syntax
		given(vendorRepository.findAll()).willReturn(vendors);
		
		//when
		VendorListDTO vendorListDTO = vendorService.getAllVendors();
		
		//then
		then(vendorRepository).should(times(1)).findAll();
		
		//JUnit Assert that with matchers
		assertThat(vendorListDTO.getVendors().size(), is(equalTo(2)));
	}
	
	@Test
	public void createNewVendor() throws Exception {
		//given
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName(NAME_1);
		
		Vendor vendor = getVendor1();
		
		//mokito BBD syntax
		given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);
		
		//when
		VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);
		
		//then
		// 'should' defaults to times = 1
		then(vendorRepository).should().save(any(Vendor.class));
		
		//JUnit Assert that with matchers
		assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
	}
	
	@Test
	public void saveVendorByDTO() throws Exception {
		//given
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName(NAME_1);
		
		Vendor vendor = getVendor1();
		
		//mokito BBD syntax
		given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);
		
		//when
		VendorDTO savedVendorDTO = vendorService.saveVendorByDTO(ID_1, vendorDTO);
		
		//then
		then(vendorRepository).should().save(any(Vendor.class));
		
		//JUnit Assert that with matchers
		assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
	}
	
	@Test
	public void patchVendor() throws Exception{
		//given
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName(NAME_1);
		
		Vendor vendor = getVendor1();
		
		//mokito BBD syntax
		given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
		given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);
		
		//when
		VendorDTO savedVendorDTO = vendorService.patchVendor(ID_1, vendorDTO);
		
		//then
		then(vendorRepository).should().findById(anyLong());
		then(vendorRepository).should().save(any(Vendor.class));
		
		assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
	}
	
	@Test
	public void deleteVendorById() throws Exception{
		
		//when
		vendorService.deleteVendorById(1L);
		
		//then
		then(vendorRepository).should().deleteById(anyLong());
		
	}
	
	private Vendor getVendor1() {
		Vendor vendor = new Vendor();
		vendor.setName(NAME_1);
		vendor.setId(ID_1);
		return vendor;
	}
	
	private Vendor getVendor2() {
		Vendor vendor = new Vendor();
		vendor.setName(NAME_2);
		vendor.setId(ID_2);
		return vendor;
	}

}
