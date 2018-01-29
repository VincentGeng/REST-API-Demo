package com.vincent.lab.rest.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Vincent Geng
 *
 * Created on 27 Jan 2018
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	/*Test it in your browser by visiting http://localhost:8080/your-app-root/swagger-ui.html*/	
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		
		Contact contact = new Contact("Vincent Geng", "https://www.vincentlab.com",
                "vincentgeng90@gmail.com");
		
		return new ApiInfo(
				"Shop Web Application RESTful API Documentation",
				"Using REST API + Swagger 2  ----  Vincent Lab",
				"1.0",
				"Terms of Service: None",
				contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList()
				);

	}
}
