package com.github.teamcalendar.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.RoleDAO;
import com.github.teamcalendar.domain.RoleEntity;

@Repository("roleDAO")
public class RoleDAOImpl extends AbstractDaoImpl<Integer, RoleEntity> implements RoleDAO
{

    @SuppressWarnings("unchecked")
    public List<RoleEntity> getAllRoles()
    {
        Criteria criteria = getCriteria();
        List<RoleEntity> roles = (List<RoleEntity>)criteria.list();

        return roles;
    }

    public RoleEntity getRoleByName(String name)
    {
        Criteria crit = getCriteria();
        crit.add(Restrictions.eq("name", name));
        RoleEntity role = (RoleEntity)crit.uniqueResult();

        return role;
    }
}
