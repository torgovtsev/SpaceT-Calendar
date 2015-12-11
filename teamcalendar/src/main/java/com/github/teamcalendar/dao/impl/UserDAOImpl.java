package com.github.teamcalendar.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.UserDAO;
import com.github.teamcalendar.domain.UserEntity;

@Repository("userDAO")
public class UserDAOImpl extends AbstractDaoImpl<Integer, UserEntity> implements UserDAO
{

    public void addUser(UserEntity user)
    {
        create(user);
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
        Criteria crit = getCriteria();
        crit.add(Restrictions.eq("email", email));
        UserEntity user = (UserEntity)crit.uniqueResult();

        return user;
    }

    public UserEntity getUserByID(Integer id)
    {
        UserEntity user = getById(id);
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<UserEntity> getAllUsers()
    {
        Criteria criteria = getCriteria();
        List<UserEntity> users = (List<UserEntity>)criteria.list();

        return users;
    }

    public Long getCountUserByEmail(String email)
    {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("email", email));
        criteria.setProjection(Projections.rowCount());
        Long count = (Long)criteria.uniqueResult();
        return count;
    }

    @Override
    public Long getCountUserByMobile(String mobile)
    {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("mobile", mobile));
        criteria.setProjection(Projections.rowCount());
        Long count = (Long)criteria.uniqueResult();
        return count;
    }

}
