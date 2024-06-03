package com.funnygorilla.tinybankcustomer.controllers.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.funnygorilla.tinybankcustomer.dto.CustomerDTO;

import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping(path = "/api/v1/customers")   
@Tag(name = "Customer-Domain", description = "provider customer CRUD service")
public interface CustomerAPI {
	
	@RequestMapping(method = RequestMethod.GET, value="/")
	public ResponseEntity<List<CustomerNumber>> getCustomers();
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<CustomerDTO> getCustomerByID(@PathVariable(required=true) long id);
	
	@RequestMapping(method = RequestMethod.POST, value="/")
	public ResponseEntity<Long> createCustomer(@RequestBody @Valid CustomerDTO student);
	
    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long id, 
		@Valid @RequestBody Customer student) ;
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") Long id);

}
