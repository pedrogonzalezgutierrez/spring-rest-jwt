package com.kiesoft.service.note;

import com.kiesoft.dto.user.UserDTO;
import com.kiesoft.service.DTOService;

public interface UserDTOService extends DTOService<UserDTO> {
	
	UserDTO findByUsername(String username);

}
