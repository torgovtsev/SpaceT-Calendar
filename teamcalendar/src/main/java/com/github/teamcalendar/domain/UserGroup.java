package com.github.teamcalendar.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "USER_GROUP", schema = "PUBLIC", catalog = "PUBLIC", uniqueConstraints = @UniqueConstraint(columnNames = {"USER_ID", "GROUP_ID" }))
public class UserGroup{

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false, insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "GROUP_ID", nullable = false, insertable = false, updatable = false)
	private Group group;

	public UserGroup() {
	}

	public UserGroup(User user,Group group) {
		this.user = user;
		this.group = group;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
