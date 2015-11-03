package com.github.teamcalendar.dao;

import java.util.List;
import java.util.Set;

import com.github.teamcalendar.domain.Group;
import com.github.teamcalendar.domain.User;



public interface GroupDAO {
	
	void addGroup(Group group);
	
	void updateRole(Group group);
	
	void deleteRole(Group group);
	
	List<Group> allGroup();
	
	Group getGroupById(Integer  id);
	
	Group getGroupByName(String name);
		
	Set<Group> getGroupForUser(User user);
	
	void updateGroupForUser(User user, List<Group> userGroups);

}