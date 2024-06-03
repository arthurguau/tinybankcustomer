package com.funnygorilla.tinybankcustomer.services.publish;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSONMessageBuilder {
	
	private JSONMessageBuilder() {
	}

	public static String buildJsonMsg (CustomerDomainEvent event) throws JsonProcessingException {
		String result = null;
		try {
			result = new ObjectMapper().writeValueAsString(event);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
