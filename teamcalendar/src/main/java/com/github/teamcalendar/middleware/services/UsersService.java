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

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(User user);

    User getUserByEmail(String email);

    User getUserByID(Integer id);

    List<User> getAllUsers();
}
