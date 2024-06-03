package com.funnygorilla.tinybankcustomer.services;  

import java.util.List;

import com.funnygorilla.tinybankcustomer.dto.CustomerDTO;


public interface CustomerService {  
	
	/**
	 * 
	 * @return
	 */
	public List<CustomerDTO> getAllCustomers();
	/**
	 * 
	 * @param id
	 * @return
	 */
	public CustomerDTO getCustomerById(Long id);	
	/**
	 * 
	 * @param student
	 * @return
	 */
	public Long createCustomer(CustomerDTO customerDTO);
	/**
	 * 
	 * @param student
	 * @return
	 */
	public CustomerDTO updateCustomer (CustomerDTO customer);
	/**
	 * 
	 * @param studentId
	 * @return
	 */
	public boolean deleteCustomer (Long customerId) ;
}  