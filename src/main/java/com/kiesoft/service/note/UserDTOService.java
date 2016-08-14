package com.kiesoft.service.note;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kiesoft.dto.metadata.MetadataUsernameDTO;
import com.kiesoft.dto.user.UserDTO;
import com.kiesoft.service.DTOService;

public interface UserDTOService extends DTOService<UserDTO> {
	
	UserDTO findByUsername(String username);
	Page<MetadataUsernameDTO> findAllByUsername(Pageable page);

}
