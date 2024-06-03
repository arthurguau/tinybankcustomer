package com.funnygorilla.tinybankcustomer.controllers.api;

import java.util.List;

public class CustomerList {
	
	public List<CustomerNumber> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerNumber> customers) {
		this.customers = customers;
	}

	List <CustomerNumber> customers = null;
	
}
