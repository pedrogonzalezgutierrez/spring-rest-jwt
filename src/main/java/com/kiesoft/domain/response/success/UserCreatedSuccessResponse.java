package com.kiesoft.domain.response.success;

import com.kiesoft.domain.response.GenericResponse;
import com.kiesoft.dto.user.UserDTO;

public interface UserCreatedSuccessResponse extends GenericResponse {
	
	UserDTO getUser();

}
