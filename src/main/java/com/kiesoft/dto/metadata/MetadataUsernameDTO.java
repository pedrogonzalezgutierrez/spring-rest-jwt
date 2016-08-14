package com.kiesoft.dto.metadata;

import com.kiesoft.domain.metadata.MetadataUsername;
import com.kiesoft.dto.AbstractDTO;

public class MetadataUsernameDTO extends AbstractDTO implements MetadataUsername {

	private String username;
	
	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

}
