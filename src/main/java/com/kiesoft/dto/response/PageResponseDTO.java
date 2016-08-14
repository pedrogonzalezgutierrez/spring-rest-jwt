package com.kiesoft.dto.response;

import org.springframework.data.domain.Page;

import com.kiesoft.domain.BaseEntity;
import com.kiesoft.domain.response.PageResponse;

public class PageResponseDTO extends GenericResponseDTO implements PageResponse {
	
	private Page<? extends BaseEntity> items;

	@Override
	public Page<? extends BaseEntity> getItems() {
		return items;
	}

	public void setItems(Page<? extends BaseEntity> items) {
		this.items = items;
	}
	
}
