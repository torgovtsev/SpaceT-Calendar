package com.github.teamcalendar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.UserDAO;
import com.github.teamcalendar.domain.RoleEntity;
import com.github.teamcalendar.domain.UserEntity;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.utils.MapperService;

@Repository("userDAO")
public class UserDAOImpl extends AbstractDao<Integer, UserEntity> implements UserDAO
{

    private static final Logger LOG = LoggerFactory.getLogger(UserEntity.class);

    @Override
    public void addUser(User user)
    {
        UserEntity data = convertRoleToEntity(user);
        persist(data);
    }

    @Override
    public void updateUser(User user)
    {
        UserEntity data = convertRoleToEntity(user);
        update(data);
    }

    @Override
    public void deleteUser(User user)
    {
        UserEntity data = convertRoleToEntity(user);
        delete(data);
    }

    @Override
    public User getUserByEmail(String email)
    {
        User res = null;
        try
        {
            Criteria crit = createEntityCriteria();
            crit.add(Restrictions.eq("email", email));
            UserEntity user = (UserEntity)crit.uniqueResult();

            if (user != null)
            {
                res = convertEntityToUser(user);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading user by email=%s", email), ex);
        }

        return res;
    }

    @Override
    public User getUserByID(Integer id)
    {
        User res = null;
        try
        {
            UserEntity data = getByKey(id);
            if (data != null)
            {
                res = convertEntityToUser(data);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading user by id=%s", id), ex);
        }

        return res;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers()
    {
        final List<User> result = new ArrayList<User>();

        Criteria criteria = createEntityCriteria();
        List<UserEntity> users = (List<UserEntity>)criteria.list();

        if (CollectionUtils.isEmpty(users))
        {
            LOG.error("NULL reference on users");
            return result;
        }

        for (UserEntity data : users)
        {
            User temp = convertEntityToUser(data);
            result.add(temp);
        }
        return result;
    }

    private User convertEntityToUser(UserEntity entity)
    {
        return MapperService.getInstance().map(entity, User.class);
    }

    private UserEntity convertRoleToEntity(User user)
    {
        return MapperService.getInstance().map(user, UserEntity.class);
    }

}
