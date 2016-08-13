package com.kiesoft.jpa.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.kiesoft.domain.user.User;
import com.kiesoft.jpa.AbstractEntity;
import com.kiesoft.jpa.note.NoteEntity;
import com.kiesoft.jpa.role.RoleEntity;

@Entity
@Table(name = "rest_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class UserEntity extends AbstractEntity implements User {

	private String username;
	private String password;
	private List<NoteEntity> notes = new ArrayList<>();
	private List<RoleEntity> roles = new ArrayList<>();
	
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rest_user_roles", joinColumns = { @JoinColumn(name = "idUser") }, inverseJoinColumns = {
			@JoinColumn(name = "idRole") })
	@Override
	public List<RoleEntity> getRoles() {
		return roles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@Override
	public List<NoteEntity> getNotes() {
		return notes;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNotes(List<NoteEntity> notes) {
		this.notes = notes;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
	
	
}
