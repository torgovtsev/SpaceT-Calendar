package com.github.teamcalendar.services;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService
{

    private static final String HELLO_WORLD = "Hello world";

    @Override
    public String getName()
    {
        return HELLO_WORLD;
    }

}
