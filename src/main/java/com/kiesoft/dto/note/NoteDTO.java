package com.kiesoft.dto.note;

import com.kiesoft.domain.note.Note;
import com.kiesoft.domain.user.User;
import com.kiesoft.dto.AbstractDTO;
import com.kiesoft.dto.user.UserDTO;

public class NoteDTO extends AbstractDTO implements Note {

	private String title;
	private String description;
	private UserDTO user;

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	

}
