/**
 * 
 */
package com.vincent.lab.rest.controllers.v1;

import static com.vincent.lab.rest.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.vincent.lab.rest.api.v1.model.VendorDTO;
import com.vincent.lab.rest.api.v1.model.VendorListDTO;
import com.vincent.lab.rest.service.VendorService;

/**
 * @author Vincent Geng
 *
 * Created on 7 Feb 2018
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerTest {
	
	@MockBean //provided by Spring Context
    private VendorService vendorService;
	
	@Autowired
    private MockMvc mockMvc; //provided by Spring Context
	
	private VendorDTO vendorDTO_1;
	private VendorDTO vendorDTO_2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		vendorDTO_1 = new VendorDTO("Vendor 1", VendorController.BASE_URL + "/1");
        vendorDTO_2 = new VendorDTO("Vendor 2", VendorController.BASE_URL + "/2");
	}

	@Test
	public void getVendorList() throws Exception {
		//given
		VendorListDTO vendorListDTO = new VendorListDTO(Arrays.asList(vendorDTO_1, vendorDTO_2));
		
		//mokito BBD syntax
		given(vendorService.getAllVendors()).willReturn(vendorListDTO);
		
		//when
		mockMvc.perform(
					get(VendorController.BASE_URL)
						.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
				.andExpect(jsonPath("$.vendors", hasSize(2)));
	}
	
	@Test
	public void getVendorById() throws Exception {
		//given
		given(vendorService.getVendorById(anyLong())).willReturn(vendorDTO_1);
		
		//when
		mockMvc.perform(
					get(VendorController.BASE_URL + "/1")
						.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
	}
	
	@Test
	public void createNewVendor() throws Exception {
		//given
		given(vendorService.createNewVendor(vendorDTO_1)).willReturn(vendorDTO_1);
		
		//when
		mockMvc.perform(
					post(VendorController.BASE_URL)
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(vendorDTO_1))
				).andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
	}
	
	@Test
    public void updateVendor() throws Exception {
		//given
        given(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO_1);
        
        //when
        mockMvc.perform(
        			put(VendorController.BASE_URL + "/1")
        				.contentType(MediaType.APPLICATION_JSON)
        				.content(asJsonString(vendorDTO_1))
        		).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

	@Test
    public void patchVendor() throws Exception {
		//given
        given(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO_1);
        
        //when
        mockMvc.perform(
        			patch(VendorController.BASE_URL + "/1")
        				.contentType(MediaType.APPLICATION_JSON)
        				.content(asJsonString(vendorDTO_1))
        		).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }
	
	@Test
    public void deleteVendor() throws Exception {
        mockMvc.perform(
        			delete(VendorController.BASE_URL + "/1")
        		).andExpect(status().isOk());

        then(vendorService).should().deleteVendorById(anyLong());
    }

}
