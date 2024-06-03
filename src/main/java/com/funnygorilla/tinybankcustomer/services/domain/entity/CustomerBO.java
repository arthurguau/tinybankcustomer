package com.funnygorilla.tinybankcustomer.services.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBO {
	private Long id;
	private String firstName;
	private String lastName;
	private boolean active;
}
