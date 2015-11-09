package com.github.teamcalendar.middleware.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.teamcalendar.middleware.dto.User;

@Service("userService")
public class UserServiceImpl implements UserService
{

    private static final String HELLO_WORLD = "Hello world";

    @Autowired
	private UsersService usersService;
    
    
    @Override
    public String getName()
    {
    	List<User> users = usersService.getAllUsers();
    	System.out.println(users);
        return HELLO_WORLD;
    }

}
