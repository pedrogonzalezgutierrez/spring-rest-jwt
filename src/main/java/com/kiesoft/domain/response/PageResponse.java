package com.kiesoft.domain.response;

import org.springframework.data.domain.Page;

import com.kiesoft.domain.BaseEntity;

public interface PageResponse extends GenericResponse {

	Page<? extends BaseEntity> getItems();
}
