package com.github.teamcalendar.domain;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "GROUP_ROLE", schema = "PUBLIC", catalog = "PUBLIC", uniqueConstraints = @UniqueConstraint(columnNames = {"GROUP_ID", "ROLE_ID" }))
public class GroupRole {

	@ManyToOne
	@JoinColumn(name = "GROUP_ID", nullable = false, insertable = false, updatable = false)
	private Group group;
	
	@ManyToOne
	@JoinColumn(name = "ROLE_ID", nullable = false, insertable = false, updatable = false)
	private Role role;

	public GroupRole() {
	}

	public GroupRole(Group group,Role role) {
		this.group = group;
		this.role = role;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}