package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.User;

public interface UserDAO {
	
	void addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(User user);

	User getUserByEmail(String email);
		
	User getUserByID(Integer id);
	
	List <User> allUsers();
	
}
