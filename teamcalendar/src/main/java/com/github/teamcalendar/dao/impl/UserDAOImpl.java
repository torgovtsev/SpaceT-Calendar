package com.github.teamcalendar.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.UserDAO;
import com.github.teamcalendar.domain.UserEntity;
import com.github.teamcalendar.middleware.dto.User;

@Repository("userDAO")
public class UserDAOImpl extends AbstractDaoImpl<Integer, UserEntity> implements UserDAO
{

    public UserEntity getUserByEmail(String email)
    {
        Criteria crit = getCriteria();
        crit.add(Restrictions.eq("email", email));
        UserEntity user = (UserEntity)crit.uniqueResult();

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

	@Override
	public UserEntity getUserByName(String first, String last) {
		Criteria crit = getCriteria();
        crit.add(Restrictions.eq("firstName", first));
        crit.add(Restrictions.eq("lastName", last));
        UserEntity user = (UserEntity)crit.uniqueResult();

        return user;
	}

}
