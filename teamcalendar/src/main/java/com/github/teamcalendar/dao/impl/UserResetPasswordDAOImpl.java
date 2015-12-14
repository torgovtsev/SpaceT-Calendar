package com.github.teamcalendar.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.UserResetPasswordDAO;
import com.github.teamcalendar.domain.UserResetPasswordEntity;
import com.github.teamcalendar.middleware.dto.User;

@Repository("userResetPasswordDAO")
public class UserResetPasswordDAOImpl extends AbstractDaoImpl<Integer, UserResetPasswordEntity> implements UserResetPasswordDAO
{

    @Override
    public UserResetPasswordEntity getUserResetPasswordByUserId(Integer id)
    {
        Criteria crit = getCriteria();
        User user = new User();
        user.setId(id);
        crit.add(Restrictions.eq("user_id.id", id));
        UserResetPasswordEntity userResetPasswordEntity = (UserResetPasswordEntity)crit.uniqueResult();
        return userResetPasswordEntity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserResetPasswordEntity> getAllUsersResetPassword()
    {
        Criteria criteria = getCriteria();
        List<UserResetPasswordEntity> usersResetPasswordEntity = (List<UserResetPasswordEntity>)criteria.list();

        return usersResetPasswordEntity;
    }

}
