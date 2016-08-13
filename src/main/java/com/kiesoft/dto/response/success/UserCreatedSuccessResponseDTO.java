package com.kiesoft.dto.response.success;

import com.kiesoft.domain.response.success.UserCreatedSuccessResponse;
import com.kiesoft.dto.user.UserDTO;
import com.kiesoft.serviceimpl.response.AbstractResponseDTO;

public class UserCreatedSuccessResponseDTO extends AbstractResponseDTO implements UserCreatedSuccessResponse {
	
	private UserDTO user;

	@Override
	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
}
