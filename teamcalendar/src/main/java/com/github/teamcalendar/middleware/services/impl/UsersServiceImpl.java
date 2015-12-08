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
import com.github.teamcalendar.domain.UserEntity;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.UsersService;
import com.github.teamcalendar.middleware.utils.MapperService;

/**
 * @author Artem Ivanov
 *
 */
@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService
{

    private static final Logger LOG = LoggerFactory.getLogger(UserEntity.class);

    @Autowired
    private UserDAO             dao;

    /**
     * @param User
     *            if a null object parameter is passed to method, nothing will happen
     */
    public void addUser(User user)
    {
        if (user == null)
        {
            return;
        }
        UserEntity userEntity = convertUserToEntity(user);
        dao.addUser(userEntity);
    }

    /**
     * @param User
     *            if a null object parameter is passed to method, nothing will happen
     */
    public void updateUser(User user)
    {
        if (user == null)
        {
            return;
        }
        UserEntity userEntity = convertUserToEntity(user);
        dao.updateUser(userEntity);
    }

    /**
     * @param User
     *            if a null object parameter is passed to method, nothing will happen
     */
    public void deleteUser(User user)
    {
        if (user == null)
        {
            return;
        }
        UserEntity userEntity = convertUserToEntity(user);
        dao.deleteUser(userEntity);
    }

    /**
     * @return <code>null</code> if user does not exist
     */
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

    /**
     * @return <code>null</code> if user does not exist
     */
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

    /**
     * @return <code>null</code> if there is no users in table
     */
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
