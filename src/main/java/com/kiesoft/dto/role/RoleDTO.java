package com.kiesoft.dto.role;

import java.util.ArrayList;
import java.util.List;

import com.kiesoft.domain.role.Role;
import com.kiesoft.dto.AbstractDTO;
import com.kiesoft.dto.user.UserDTO;

public class RoleDTO extends AbstractDTO implements Role {

	private String rolename;
	private List<UserDTO> users = new ArrayList<>();
	
	@Override
	public String getRolename() {
		return rolename;
	}
	
	@Override
	public List<UserDTO> getUsers() {
		return users;
	}
	
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

}
