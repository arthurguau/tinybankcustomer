package com.funnygorilla.tinybankcustomer.repository.dataaccess;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity  
@Table(name = "CUSTOMER")
public class CustomerDB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="PERSON_ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "ACTIVE", nullable = false, columnDefinition = "boolean default true")
	private boolean active;
}
