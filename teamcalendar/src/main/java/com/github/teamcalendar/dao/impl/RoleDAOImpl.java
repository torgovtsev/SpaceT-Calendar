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

import com.github.teamcalendar.dao.RoleDAO;
import com.github.teamcalendar.domain.PermissionEntity;
import com.github.teamcalendar.domain.QuestionEntity;
import com.github.teamcalendar.domain.RoleEntity;
import com.github.teamcalendar.middleware.dto.Question;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.utils.MapperService;

@Repository("roleDAO")
public class RoleDAOImpl extends AbstractDaoImpl<Integer, RoleEntity> implements RoleDAO
{

    public void addRole(RoleEntity role)
    {
        create(role);
    }

    public void updateRole(RoleEntity role)
    {
        update(role);
    }

    public void deleteRole(RoleEntity role)
    {
        delete(role);
    }

    @SuppressWarnings("unchecked")
    public List<RoleEntity> getAllRoles()
    {
        Criteria criteria = getCriteria();
        List<RoleEntity> roles = (List<RoleEntity>)criteria.list();

        return roles;
    }

    public RoleEntity getRoleById(Integer id)
    {
        RoleEntity role = getById(id);
        return role;
    }

    public RoleEntity getRoleByName(String name)
    {
        Criteria crit = getCriteria();
        crit.add(Restrictions.eq("name", name));
        RoleEntity role = (RoleEntity)crit.uniqueResult();

        return role;
    }
}
