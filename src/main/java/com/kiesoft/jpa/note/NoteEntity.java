package com.kiesoft.jpa.note;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kiesoft.domain.note.Note;
import com.kiesoft.jpa.AbstractEntity;
import com.kiesoft.jpa.user.UserEntity;

@Entity
@Table(name = "rest_notes")
public class NoteEntity extends AbstractEntity implements Note {

	private String title;
	private String description;
	private UserEntity user;
	
	@ManyToOne
	@Override
	public UserEntity getUser() {
		return user;
	}

	@Column(length=200)
	@Override
	public String getTitle() {
		return title;
	}

	@Column(length=10000)
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

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
