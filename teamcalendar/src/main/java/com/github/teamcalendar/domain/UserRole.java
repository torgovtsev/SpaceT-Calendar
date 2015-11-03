package com.github.teamcalendar.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "USER_ROLE", schema = "PUBLIC", catalog = "PUBLIC", uniqueConstraints = @UniqueConstraint(columnNames = {"USER_ID", "ROLE_ID" }))
public class UserRole{

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false, insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "ROLE_ID", nullable = false, insertable = false, updatable = false)
	private Role role;

	public UserRole() {
	}

	public UserRole(User user,Role role) {
		this.user = user;
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}