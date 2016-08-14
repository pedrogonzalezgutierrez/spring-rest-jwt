package com.kiesoft.dto.user;

import java.util.ArrayList;
import java.util.List;

import com.kiesoft.domain.user.User;
import com.kiesoft.dto.metadata.MetadataUsernameDTO;
import com.kiesoft.dto.note.NoteDTO;
import com.kiesoft.dto.role.RoleDTO;

public class UserDTO extends MetadataUsernameDTO implements User {

	private String password;
	private List<RoleDTO> roles = new ArrayList<>();
	private List<NoteDTO> notes = new ArrayList<>();

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public List<RoleDTO> getRoles() {
		return roles;
	}

	@Override
	public List<NoteDTO> getNotes() {
		return notes;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public void setNotes(List<NoteDTO> notes) {
		this.notes = notes;
	}
	
}
