package com.vincent.lab.rest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vincent.lab.rest.api.v1.model.VendorDTO;
import com.vincent.lab.rest.api.v1.model.VendorListDTO;
import com.vincent.lab.rest.service.VendorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Vincent Geng
 *
 * Created on 27 Jan 2018
 */
@RestController
@RequestMapping(VendorController.BASE_URL)
@Api(value="Operations pertaining to vendors in Online Store")
public class VendorController {
	
	public static final String BASE_URL = "/api/v1/vendors";
	
	private final VendorService vendorService;

	public VendorController(VendorService vendorService) {
		this.vendorService = vendorService;
	}
	
	@ApiOperation(value = "View a list of available vendors",response = VendorListDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved vendor list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public VendorListDTO getVendorList(){
		return vendorService.getAllVendors();
	}
	
	@ApiOperation(value = "Search a vendor with an ID",response = VendorDTO.class)
	@GetMapping({"/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO getVendorById(@ApiParam(value = "ID of vendor that needs to be fetched", required = true)@PathVariable Long id){
		return vendorService.getVendorById(id);
	}
	
	@ApiOperation(value = "Add a vendor")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO){
		return vendorService.createNewVendor(vendorDTO);
	}
	
	@ApiOperation(value = "Update a vendor")
	@PutMapping({"/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO updateVendor(@ApiParam(value = "ID of vendor that needs to be updated", required = true)@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
		return vendorService.saveVendorByDTO(id, vendorDTO);
	}
	
	@ApiOperation(value = "Patch a vendor")
	@PatchMapping({"/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO patchVendor(@ApiParam(value = "ID of vendor that needs to be patched", required = true)@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
		return vendorService.saveVendorByDTO(id, vendorDTO);
	}
	
	@ApiOperation(value = "Delete a vendor")
	@DeleteMapping({"/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public void deleteVendor(@PathVariable Long id){
		vendorService.deleteVendorById(id);
	}
}
