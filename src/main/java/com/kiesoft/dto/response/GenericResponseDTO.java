package com.kiesoft.dto.response;

import com.kiesoft.domain.response.GenericResponse;

public class GenericResponseDTO implements GenericResponse {

	private String message;
	
	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
