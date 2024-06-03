package com.funnygorilla.tinybankcustomer.services;  

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funnygorilla.tinybankcustomer.dto.CustomerDTO;
import com.funnygorilla.tinybankcustomer.repository.CustomerRepository;
import com.funnygorilla.tinybankcustomer.services.domain.entity.CustomerBO;
import com.funnygorilla.tinybankcustomer.services.publish.CustomerDomainEvent;
import com.funnygorilla.tinybankcustomer.services.publish.CustomerDomainEventPublisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service  
public class CustomerServiceImpl implements CustomerService {  
	
	@Autowired  
	private CustomerRepository customerRepository;  
	@Autowired
	private CustomerDomainEventPublisher publish;
	
	@Override
	public List<CustomerDTO> getAllCustomers()
	{  
		List <CustomerDTO> all = new ArrayList<CustomerDTO>();  
		List <CustomerBO> bos = this.customerRepository.getAllCustomers();
		
		CustomerDTO dto = null;
		
		Iterator<CustomerBO> r = bos.iterator();
		while (r.hasNext()) {
			dto = Converter.fromCustomerBOToCustomerDTO(r.next());
			all.add(dto);
		}
			
		return all;  
	}  
	
	@Override
	public CustomerDTO getCustomerById(Long id)
	{  
		log.debug(" ---------------> getCustomerByID() called in Service. ");
		CustomerBO studentBO = this.customerRepository.getCustomerByID(id);
		CustomerDTO dto = Converter.fromCustomerBOToCustomerDTO(studentBO);		
		return dto;
	}  
	
	@Override
	public Long createCustomer(CustomerDTO customerDto){  
		log.debug(" ---------------> [Customer Service] createCustomer() before save(). ");		
		CustomerBO sbo = Converter.fromCustomerDTOToCustomerBO(customerDto);
		
		Long id = this.customerRepository.createCustomer(sbo);
		log.debug(" ---------------> [Customer Service] createCustomer() after save(). ");
		
		CustomerDomainEvent event = CustomerDomainEvent.builder()
				.eventType(CustomerDomainEvent.EventType.CREATE)
				.customerId(id)
				.firstName(sbo.getFirstName())
				.lastName(sbo.getLastName())
				.active(sbo.isActive())
				.build();
		publish.publish(event);
		return id;
	}  
	
	@Override
	public CustomerDTO updateCustomer (CustomerDTO customerDto ) {
		
		if (null == customerDto || null == customerDto.getId()) {
			// throw exception
		}
		
		CustomerBO customerBO = Converter.fromCustomerDTOToCustomerBO(customerDto);		
		customerBO = this.customerRepository.updateCustomer(customerBO);
		customerDto = Converter.fromCustomerBOToCustomerDTO(customerBO);		
		return  customerDto;
	}
	
	@Override
	public boolean deleteCustomer (Long studentId) {
		return this.customerRepository.deleteCustomer(studentId);
	}
	
	/**
	 * 
	 * @author guson
	 *
	 */
	private static class Converter {
		
		public static CustomerBO fromCustomerDTOToCustomerBO (CustomerDTO cutomerDTO) {
			CustomerBO customerBO = new CustomerBO();
			// simple converter logic.
		    BeanUtils.copyProperties(cutomerDTO, customerBO);
		    return customerBO;
	    }
		
	    public static CustomerDTO fromCustomerBOToCustomerDTO (CustomerBO customer) {
			CustomerDTO customerDTO = new CustomerDTO();
			// simple converter logic.
		    BeanUtils.copyProperties(customer, customerDTO);
		    return customerDTO;
	    }
	}

}  