package com.github.teamcalendar.middleware.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.UserDAO;
import com.github.teamcalendar.domain.GroupEntity;
import com.github.teamcalendar.domain.UserEntity;
import com.github.teamcalendar.middleware.dto.Group;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.UsersService;
import com.github.teamcalendar.middleware.utils.MapperService;

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService
{

    private static final Logger LOG = LoggerFactory.getLogger(UserEntity.class);

    @Autowired
    private UserDAO             dao;

    public void addUser(User user)
    {
        UserEntity userEntity = convertUserToEntity(user);
        dao.addUser(userEntity);
    }

    public void updateUser(User user)
    {
        UserEntity userEntity = convertUserToEntity(user);
        dao.updateUser(userEntity);
    }

    public void deleteUser(User user)
    {
        UserEntity userEntity = convertUserToEntity(user);
        dao.deleteUser(userEntity);
    }

    public User getUserByEmail(String email)
    {

        User result = null;
        try
        {
            UserEntity userEntity = (UserEntity)dao.getUserByEmail(email);

            if (userEntity != null)
            {
                result = convertEntityToUser(userEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading user by email=%s", email), ex);
        }

        return result;
    }

    public User getUserByID(Integer id)
    {

        User result = null;
        try
        {
            UserEntity userEntity = (UserEntity)dao.getUserByID(id);
            if (userEntity != null)
            {
                result = convertEntityToUser(userEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading user by id=%s", id), ex);
        }

        return result;
    }

    public List<User> getAllUsers()
    {

        final List<User> result = new ArrayList<User>();

        List<UserEntity> usersEntity = dao.getAllUsers();

        if (CollectionUtils.isEmpty(usersEntity))
        {
            LOG.error("NULL reference on users");
            return result;
        }

        for (UserEntity data : usersEntity)
        {
            User user = convertEntityToUser(data);
            result.add(user);
        }
        return result;
    }

    private User convertEntityToUser(UserEntity entity)
    {
        return MapperService.getInstance().map(entity, User.class);
    }

    private UserEntity convertUserToEntity(User user)
    {
        return MapperService.getInstance().map(user, UserEntity.class);
    }

}
