package com.kiesoft.dto.response;

import java.util.List;

import com.kiesoft.domain.response.error.ErrorResponse;
import com.kiesoft.serviceimpl.response.AbstractResponseDTO;

public class ErrorResponseDTO extends AbstractResponseDTO implements ErrorResponse {
	
	private List<String> messages;
	
	@Override
	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
}
