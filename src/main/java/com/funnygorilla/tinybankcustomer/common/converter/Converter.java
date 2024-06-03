package com.funnygorilla.tinybankcustomer.common.converter;

import org.springframework.beans.BeanUtils;

import com.funnygorilla.tinybankcustomer.controllers.api.Customer;
import com.funnygorilla.tinybankcustomer.controllers.api.CustomerNumber;
import com.funnygorilla.tinybankcustomer.dto.CustomerDTO;

public class Converter<S, V>  {

	public static Customer fromStudnetDTOToStudent (CustomerDTO studentDTO) {
		Customer student = new Customer();
		// simple converter logic.
	    BeanUtils.copyProperties(studentDTO, student);
	    
	    return student;
    }

    public static CustomerDTO fromStudentToStudentDTO (Customer student) {
		CustomerDTO studentDTO = new CustomerDTO();
		// simple converter logic.
	    BeanUtils.copyProperties(student, studentDTO);
	    return studentDTO;
    }
 
	public static CustomerNumber fromStudentDTOToStudentNumber (CustomerDTO s) {
		if (null == s) return null;
		
		CustomerNumber number = new CustomerNumber();
		number.setId(s.getId());
		return number;
	}
	
	public static CustomerDTO fromStudentNumberToStudentDTO (CustomerNumber sn) {
		 throw new AssertionError("does not support this method!"); 
	}

    
    
}