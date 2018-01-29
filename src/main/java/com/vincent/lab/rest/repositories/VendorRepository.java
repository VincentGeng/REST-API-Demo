package com.vincent.lab.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vincent.lab.rest.domain.Vendor;

/**
 * @author Vincent Geng
 *
 * Created on 27 Jan 2018
 */
public interface VendorRepository extends JpaRepository<Vendor, Long> {
	
}
