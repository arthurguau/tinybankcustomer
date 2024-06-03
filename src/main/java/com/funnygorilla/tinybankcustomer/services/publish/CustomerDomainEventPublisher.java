package com.funnygorilla.tinybankcustomer.services.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerDomainEventPublisher {
	private static final String CUSTOMER_EVENT = "Customer event fired. Event String => {}";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value(value = "${kafka.topic}")
	private String topicName;
	
	public boolean publish (CustomerDomainEvent cutomerEvent) {
		String customerMsg = null;
		
		try {
			customerMsg = JSONMessageBuilder.buildJsonMsg(cutomerEvent);
			System.out.println("----------------------------> " + customerMsg);
		} catch (JsonProcessingException e) {
		    log.error("Customer Event parsing error: " + e.getMessage());	
			return false;
		}
		
		this.kafkaTemplate.send(topicName, customerMsg);
		log.info(CUSTOMER_EVENT, customerMsg);
		
		return true;
		
	}
}
