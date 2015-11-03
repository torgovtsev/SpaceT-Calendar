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
@Table(name = "GROUP_", schema = "PUBLIC", catalog = "PUBLIC", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "NAME", unique = true, nullable = false, length = 128)
	private String name;
	
	@Column(name = "DESCRIPTION", length = 512)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	private Set<GroupRole> groupRole = new HashSet<GroupRole>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	private Set<UserGroup> userGroup = new HashSet<UserGroup>(0);
	
	public Group() {
	}

	public Group(String name) {
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
	
	public Set<GroupRole> getGroupRole() {
		return this.groupRole;
	}

	public void setGroupRole(Set<GroupRole> groupRole) {
		this.groupRole = groupRole;
	}

	public Set<UserGroup> getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(Set<UserGroup> userGroup) {
		this.userGroup = userGroup;
	}

}