package com.kiesoft.jpa.role;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.kiesoft.domain.role.Role;
import com.kiesoft.jpa.AbstractEntity;
import com.kiesoft.jpa.user.UserEntity;

@Entity
@Table(name = "rest_role", uniqueConstraints = { @UniqueConstraint(columnNames = { "rolename" }) })
public class RoleEntity extends AbstractEntity implements Role {

	private String rolename;
	private List<UserEntity> users = new ArrayList<>();

	@Override
	public String getRolename() {
		return rolename;
	}

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	@Override
	public List<UserEntity> getUsers() {
		return users;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

}