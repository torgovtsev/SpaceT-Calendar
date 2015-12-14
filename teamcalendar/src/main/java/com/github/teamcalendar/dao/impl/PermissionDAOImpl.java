package com.github.teamcalendar.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.PermissionDAO;
import com.github.teamcalendar.domain.PermissionEntity;

@Repository("permissionDAO")
public class PermissionDAOImpl extends AbstractDaoImpl<Integer, PermissionEntity> implements PermissionDAO
{

    @SuppressWarnings("unchecked")
    public List<PermissionEntity> getAllPermissions()
    {
        Criteria criteria = getCriteria();
        List<PermissionEntity> permissions = (List<PermissionEntity>)criteria.list();

        return permissions;
    }

    public PermissionEntity getPermissionByName(String name)
    {

        Criteria crit = getCriteria();
        crit.add(Restrictions.eq("name", name));
        PermissionEntity permission = (PermissionEntity)crit.uniqueResult();

        return permission;
    }

}
