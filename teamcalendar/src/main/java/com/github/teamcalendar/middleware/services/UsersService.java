package com.github.teamcalendar.middleware.services;

import java.util.List;

import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.impl.UsersServiceImpl;

/**
 * @author Artem Ivanov
 * @see {@link UsersServiceImpl}
 */
public interface UsersService
{

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getUserByEmail(String email);

    User getUserByID(Integer id);

    List<User> getAllUsers();
}
