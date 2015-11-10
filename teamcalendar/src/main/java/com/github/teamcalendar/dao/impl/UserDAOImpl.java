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
import com.github.teamcalendar.domain.GroupEntity;
import com.github.teamcalendar.domain.RoleEntity;
import com.github.teamcalendar.domain.UserEntity;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.utils.MapperService;

@Repository("userDAO")
public class UserDAOImpl extends AbstractDao<Integer, UserEntity> implements UserDAO
{

    public void addUser(UserEntity user)
    {
        persist(user);
    }

    public void updateUser(UserEntity user)
    {
        update(user);
    }

    public void deleteUser(UserEntity user)
    {
        delete(user);
    }

    public UserEntity getUserByEmail(String email)
    {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("email", email));
        UserEntity user = (UserEntity)crit.uniqueResult();

        return user;
    }

    public UserEntity getUserByID(Integer id)
    {
        UserEntity user = getByKey(id);
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<UserEntity> getAllUsers()
    {
        Criteria criteria = createEntityCriteria();
        List<UserEntity> users = (List<UserEntity>)criteria.list();

        return users;
    }

}
