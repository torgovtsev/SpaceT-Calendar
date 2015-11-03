package com.github.teamcalendar.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ROLE_", schema = "PUBLIC", catalog = "PUBLIC", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "NAME", unique = true, nullable = false, length = 128)
	private String name;
	
	@Column(name = "DESCRIPTION", length = 512)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private Set<RolePermission> rolePermission = new HashSet<RolePermission>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private Set<GroupRole> groupRole = new HashSet<GroupRole>(0);
	
	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<RolePermission> getRolePermission() {
		return this.rolePermission;
	}

	public void setRolePermissionEntities(
			Set<RolePermission> rolePermission) {
		this.rolePermission = rolePermission;
	}

	public Set<UserRole> getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public Set<GroupRole> getGroupRole() {
		return this.groupRole;
	}

	public void setGroupRole(Set<GroupRole> groupRole) {
		this.groupRole = groupRole;
	}

}