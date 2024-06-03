package com.funnygorilla.tinybankcustomer.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.funnygorilla.tinybankcustomer.controllers.api.Customer;
import com.funnygorilla.tinybankcustomer.controllers.api.CustomerAPI;
import com.funnygorilla.tinybankcustomer.controllers.api.CustomerNumber;
import com.funnygorilla.tinybankcustomer.dto.CustomerDTO;
import com.funnygorilla.tinybankcustomer.services.CustomerService;

@RestController
public class CustomerController implements CustomerAPI {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired  
	private CustomerService customerService;
	
	@Override
	public ResponseEntity<List<CustomerNumber>> getCustomers() {
		
		List <CustomerNumber> studentNumbers = new ArrayList<CustomerNumber>();
		
		List <CustomerDTO> customers = this.customerService.getAllCustomers();	
		
		if (customers == null || customers.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		logger.debug(" --> " + " getCustomers() returned: \n" + customers.toString());
		
		// compose response: DTO -> Response
		Iterator<CustomerDTO> ite = customers.iterator();
		while (ite.hasNext()) {
			studentNumbers.add(Converter.fromCustomerDTOToCustomerNumber(ite.next()));
		}
		
		return new ResponseEntity<>(studentNumbers, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<CustomerDTO> getCustomerByID(@PathVariable(required=true) long id) {
		
		CustomerDTO s = this.customerService.getCustomerById(id);
		logger.debug(" --> " + " getCustomerByID() " + s.toString());
		
		if (s == null || s.getId() == null){
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		} 
		
		return new ResponseEntity<>(s, HttpStatus.OK);
		
	}
	
	@Override
	public ResponseEntity<Long> createCustomer(@RequestBody @Valid CustomerDTO student) {
		
//		for (ObjectError error : bindingResult.getAllErrors()) {
//	        return error.getDefaultMessage();
//		}
		
        Long id = this.customerService.createCustomer(student);
        logger.debug(" --> " + " customer with " + id+ " created." );
        
        CustomerNumber response = new CustomerNumber();
        response.setId(id);
        
        return new ResponseEntity<>(id, HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long id, 
		@Valid @RequestBody Customer customer) {		
		
    	// check customer exists? 
		CustomerDTO s = this.customerService.getCustomerById(id);	
		if (s == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		// update customer otherwise
		s = Converter.fromCustomerToCustomerDTO(customer);		
		s = this.customerService.updateCustomer(s);
		
		logger.debug(" --> " + " customer " + s.toString() + " has been updated." );

	    return new ResponseEntity<>(s, HttpStatus.ACCEPTED);		
	}	
	
	@Override
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") Long id) {	
	    Boolean result = this.customerService.deleteCustomer(id);
        
        if (!result) {
        	logger.debug(" --> " + " customer: " + id + " is NOT FOUNT." );
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.debug(" --> " + " customer: " + id + " has been deleted." );
        
        return new ResponseEntity<>(HttpStatus.OK);
	}	
	
	/**
	 * 
	 * @author guson
	 *
	 */
	private static class Converter {
		
//		public static Customer fromCustomerDTOToCustomer (CustomerDTO cutomerDTO) {
//			Customer customer = new Customer();
//			// simple converter logic.
//		    BeanUtils.copyProperties(cutomerDTO, customer);
//		    return customer;
//	    }
		
		public static CustomerNumber fromCustomerDTOToCustomerNumber (CustomerDTO cutomerDTO) {
			CustomerNumber customerNum = new CustomerNumber();
			// simple converter logic.
			customerNum.setId(cutomerDTO.getId());
		    return customerNum;
	    }

	    public static CustomerDTO fromCustomerToCustomerDTO (Customer customer) {
			CustomerDTO customerDTO = new CustomerDTO();
			// simple converter logic.
		    BeanUtils.copyProperties(customer, customerDTO);
		    return customerDTO;
	    }
	}
}
