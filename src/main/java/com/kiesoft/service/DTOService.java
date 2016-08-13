package com.kiesoft.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DTOService<EntityDTO> {
	
	EntityDTO save(EntityDTO dto);
	
	EntityDTO findOne(Long id);
	Page<EntityDTO> findAll(Pageable page);

}
