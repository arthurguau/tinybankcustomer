package com.funnygorilla.tinybankcustomer.controllers.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	private Long id;
	
	@NotNull(message = "first name can not be null.")
	@Size(min = 5, max = 10, message = "the lenth of name must be between 1 and 10 characters.")
	private String fname;
	
	@NotNull(message = "last name can not be null.")
	@Size(min = 5, max = 10, message = "the lenth of name must be between 1 and 10 characters.")	
	private String lname;
	
	@NotNull(message = "active can not be null.")
	private boolean active;
}
