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
@Table(name = "PERMISSION", schema = "PUBLIC", catalog = "PUBLIC", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "NAME", unique = true, nullable = false, length = 64)
	private String name;
	
	@Column(name = "DESCRIPTION", length = 512)
	private String description;
	
	private Set<RolePermission> rolePermission = new HashSet<RolePermission>(0);
	
	public Permission() {
	}

	public Permission(String name) {
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "permission")
	public Set<RolePermission> getRolePermission() {
		return this.rolePermission;
	}

	public void setRolePermission(
			Set<RolePermission> rolePermission) {
		this.rolePermission = rolePermission;
	}

}
