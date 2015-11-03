package com.github.teamcalendar.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ROLE_PERMISSION", schema = "PUBLIC", catalog = "PUBLIC", uniqueConstraints = @UniqueConstraint(columnNames = {"ROLE_ID", "PERMISSION_ID" }))
public class RolePermission {

	@ManyToOne
	@JoinColumn(name = "PERMISSION_ID", nullable = false, insertable = false, updatable = false)
	private Permission permission;
	
	@ManyToOne
	@JoinColumn(name = "ROLE_ID", nullable = false, insertable = false, updatable = false)
	private Role role;

	public RolePermission() {
	}

	public RolePermission(Permission permission, Role role) {
		this.permission = permission;
		this.role = role;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}