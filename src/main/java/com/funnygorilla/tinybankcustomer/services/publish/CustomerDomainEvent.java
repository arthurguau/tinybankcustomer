package com.funnygorilla.tinybankcustomer.services.publish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDomainEvent {
	
	public enum EventType {CREATE, UPDATE, DELETE}
	
	private EventType eventType;
	private Long customerId;
	private String firstName;
	private String lastName;
	private boolean active;
	

}
