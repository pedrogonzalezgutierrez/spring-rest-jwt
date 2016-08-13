package com.kiesoft.serviceimpl.response;

import com.kiesoft.domain.response.GenericResponse;

public abstract class AbstractResponseDTO implements GenericResponse {

	private String defaultMessage;
	
	@Override
	public String getDefaultMessage() {
		return defaultMessage;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

}
