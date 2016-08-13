package com.kiesoft.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CRUDService<Entity> {
	
	Entity save(Entity dto);
	void delete(Long id);
	
	Entity findOne(Long id);
	Page<Entity> findAll(Pageable page);

}
