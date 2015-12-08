package com.github.teamcalendar.middleware.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.teamcalendar.dao.UserDAO;
import com.github.teamcalendar.dao.UserResetPasswordDAO;
import com.github.teamcalendar.domain.UserEntity;
import com.github.teamcalendar.domain.UserResetPasswordEntity;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.dto.UserResetPassword;
import com.github.teamcalendar.middleware.services.UserResetPasswordService;
import com.github.teamcalendar.middleware.utils.MapperService;

/**
 * @author Ivan Kopylov
 *         This service operates table which contains temporary codes for reset passwords
 */
@Service("userResetPasswordService")
@Transactional
public class UserResetPasswordServiceImpl implements UserResetPasswordService
{

    private static final Logger  LOG = LoggerFactory.getLogger(UserResetPasswordEntity.class);

    @Autowired
    private UserResetPasswordDAO dao;

    @Autowired
    private UserDAO              userdao;

    /**
     * @param userResetPassword
     *            if a null object parameter is passed to method, nothing will happen
     */
    @Override
    public void addUserResetPassword(UserResetPassword userResetPassword)
    {
        if (userResetPassword == null)
        {
            return;
        }
        UserResetPasswordEntity userResetPasswordEntity = convertUserResetPasswordToEntity(userResetPassword);
        dao.addUserResetPassword(userResetPasswordEntity);

    }

    /**
     * @param userResetPassword
     *            if a null object parameter is passed to method, nothing will happen
     */
    @Override
    public void updateUserResetPassword(UserResetPassword userResetPassword)
    {
        if (userResetPassword == null)
        {
            return;
        }
        User user = userResetPassword.getUser_id();
        Integer id = user.getId();
        UserEntity userEntity = userdao.getUserByID(id);

        UserResetPasswordEntity userResetPasswordEntity = convertUserResetPasswordToEntity(userResetPassword);
        userResetPasswordEntity.setUser_id(userEntity);

        dao.updateUserResetPassword(userResetPasswordEntity);
    }

    /**
     * @param userResetPassword
     *            if a null object parameter is passed to method, nothing will happen
     */
    @Override
    public void deleteUserResetPassword(UserResetPassword userResetPassword)
    {
        if (userResetPassword == null)
        {
            return;
        }
        UserResetPasswordEntity userResetPasswordEntity = convertUserResetPasswordToEntity(userResetPassword);
        dao.deleteUserResetPassword(userResetPasswordEntity);
    }

    /**
     * @return <code>null</code> if there is no entry for user_id (PK of User table)
     */
    @Override
    public UserResetPassword getUserResetPasswordByUserId(Integer id)
    {
        UserResetPassword result = null;

        try
        {
            UserResetPasswordEntity userResetPasswordEntity = dao.getUserResetPasswordByUserId(id);

            if (userResetPasswordEntity != null)
            {
                result = convertEntityToUserResetPassword(userResetPasswordEntity);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            LOG.error(String.format("Error loading UserResetPassword by user_id=%s", id), e);
        }

        return result;
    }

    /**
     * @return <code>null</code> if there is no entires in table
     */
    @Override
    public List<UserResetPassword> getAllUsersResetPassword()
    {
        List<UserResetPassword> result = new ArrayList<UserResetPassword>();

        List<UserResetPasswordEntity> userResetPasswordEntities = dao.getAllUsersResetPassword();

        if (CollectionUtils.isEmpty(userResetPasswordEntities))
        {
            LOG.error("Null reference on userResetPasswordEntities");
            return result;
        }

        for (UserResetPasswordEntity data : userResetPasswordEntities)
        {
            UserResetPassword userResetPassword = convertEntityToUserResetPassword(data);
            result.add(userResetPassword);
        }

        return result;
    }

    private UserResetPassword convertEntityToUserResetPassword(UserResetPasswordEntity entity)
    {
        return MapperService.getInstance().map(entity, UserResetPassword.class);
    }

    private UserResetPasswordEntity convertUserResetPasswordToEntity(UserResetPassword user)
    {
        return MapperService.getInstance().map(user, UserResetPasswordEntity.class);
    }

}
