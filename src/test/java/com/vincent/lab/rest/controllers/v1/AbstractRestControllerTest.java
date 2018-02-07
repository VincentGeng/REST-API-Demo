/**
 * 
 */
package com.vincent.lab.rest.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Vincent Geng
 *
 * Created on 7 Feb 2018
 */
public abstract class AbstractRestControllerTest {
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
}
