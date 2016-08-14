package com.kiesoft.dto.response;

import java.util.List;

import com.kiesoft.domain.response.MessagesResponse;

public class MessagesResponseDTO extends GenericResponseDTO implements MessagesResponse {
	
	private List<String> items;

	@Override
	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}
	
}
