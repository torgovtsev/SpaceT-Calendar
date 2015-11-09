package com.github.teamcalendar.middleware.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.teamcalendar.dao.UserDAO;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.UsersService;

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService
{

    //private static final Logger LOG = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    private UserDAO dao;

    public void addUser(User user)
    {
        dao.addUser(user);
    }

    public void updateUser(User user)
    {
        dao.updateUser(user);
    }

    public void deleteUser(User user)
    {
        dao.deleteUser(user);
    }

    public User getUserByEmail(String email)
    {
        return dao.getUserByEmail(email);
    }

    public User getUserByID(Integer id)
    {
        return dao.getUserByID(id);
    }

    public List<User> getAllUsers()
    {
        return dao.getAllUsers();
    }

    //	private User mapUserEntityToUser(UserEntity entity)
    //	{
    //		Mapper mapper = MapperService.getInstance();
    //		
    //		return mapper.map(entity, User.class);
    //	}
}
