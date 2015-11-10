package com.github.teamcalendar.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.PermissionDAO;
import com.github.teamcalendar.domain.PermissionEntity;
import com.github.teamcalendar.domain.QuestionEntity;

import com.github.teamcalendar.middleware.dto.Group;
import com.github.teamcalendar.middleware.dto.Permission;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.utils.MapperService;

@Repository("permissionDAO")
public class PermissionDAOImpl extends AbstractDao<Integer, PermissionEntity> implements PermissionDAO
{

    public void addPermission(PermissionEntity permission)
    {
        persist(permission);
    }

    public void updatePermission(PermissionEntity permission)
    {
        update(permission);
    }

    public void deletePermission(PermissionEntity permission)
    {
        delete(permission);
    }

    @SuppressWarnings("unchecked")
    public List<PermissionEntity> getAllPermissions()
    {
        Criteria criteria = createEntityCriteria();
        List<PermissionEntity> permissions = (List<PermissionEntity>)criteria.list();

        return permissions;
    }

    public PermissionEntity getPermissionById(Integer id)
    {

        PermissionEntity permission = getByKey(id);
        return permission;
    }

    public PermissionEntity getPermissionByName(String name)
    {

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        PermissionEntity permission = (PermissionEntity)crit.uniqueResult();

        return permission;
    }

}
