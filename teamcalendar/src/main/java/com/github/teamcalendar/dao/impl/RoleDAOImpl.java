package com.github.teamcalendar.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.RoleDAO;
import com.github.teamcalendar.domain.QuestionEntity;
import com.github.teamcalendar.domain.RoleEntity;
import com.github.teamcalendar.domain.UserEntity;
import com.github.teamcalendar.domain.UserRoleEntity;
import com.github.teamcalendar.middleware.dto.Question;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.utils.MapperService;

@Repository("roleDAO")
public class RoleDAOImpl extends AbstractDao<Integer, RoleEntity> implements RoleDAO
{

    private static final Logger LOG = LoggerFactory.getLogger(RoleEntity.class);

    @Override
    public void addRole(Role role)
    {
        RoleEntity data = convertRoleToEntity(role);
        persist(data);
    }

    @Override
    public void updateRole(Role role)
    {
        RoleEntity data = convertRoleToEntity(role);
        update(data);
    }

    @Override
    public void deleteRole(Role role)
    {
        RoleEntity data = convertRoleToEntity(role);
        delete(data);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getAllRoles()
    {
        final List<Role> result = new ArrayList<Role>();

        Criteria criteria = createEntityCriteria();
        List<RoleEntity> roles = (List<RoleEntity>)criteria.list();

        if (CollectionUtils.isEmpty(roles))
        {
            LOG.error("NULL reference on users");
            return result;
        }

        for (RoleEntity data : roles)
        {
            Role role = convertEntityToRole(data);
            result.add(role);
        }

        return result;
    }

    @Override
    public Role getRoleById(Integer id)
    {
        Role result = null;

        try
        {
            RoleEntity role = getByKey(id);
            if (role != null)
            {
                result = convertEntityToRole(role);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading user by id=%s", id), ex);
        }

        return result;
    }

    @Override
    public Role getRoleByName(String name)
    {
        Role result = null;

        try
        {
            Criteria crit = createEntityCriteria();
            crit.add(Restrictions.eq("name", name));
            RoleEntity role = (RoleEntity)crit.uniqueResult();
            if (role != null)
            {
                result = convertEntityToRole(role);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading role by name=%s", name), ex);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Role> getRolesForUser(User user)
    {
        String email = user.getEmail();
        String hql = "select r from RoleEntity r join r.userRoleEntity ur join ur.user u where u.email = :email";
        Query query = createEntityQuery(hql).setParameter("email", email);
        Set<RoleEntity> roles = (Set<RoleEntity>)query.list();

        Set<Role> result = new HashSet<Role>();

        for (RoleEntity rol : roles)
        {
            Role data = convertEntityToRole(rol);
            result.add(data);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void updateRoleForUser(User user, List<Role> userRoles)
    {

        //		UserEntity userEntity = MapperService.getInstance().map(user, UserEntity.class);
        //		
        //		String hql = "select ur from userRoleEntity ur where ur.user = :user";
        //		Query query = createEntityQuery(hql).setParameter("user", userEntity);
        //		List<UserRoleEntity> oldRoles = (List<UserRoleEntity>) query.list();	
        //		
        //		for(UserRoleEntity role: oldRoles){
        //			delete(role);
        //		}
        //		
        //		Set<UserRoleEntity> resultUserRoleEntity = new HashSet<UserRoleEntity>();
        //		
        //		for(Role r: userRoles){
        //			RoleEntity roleEntity = MapperService.getInstance().map(r, RoleEntity.class);
        //			
        //			UserRoleEntity userRole = new UserRoleEntity(userEntity, roleEntity);
        //			persist(userRole);
        //			resultUserRoleEntity.add(userRole);
        //		}
        //		userEntity.setUserRoleEntity(resultUserRoleEntity);
    }

    private Role convertEntityToRole(RoleEntity entity)
    {
        return MapperService.getInstance().map(entity, Role.class);
    }

    private RoleEntity convertRoleToEntity(Role role)
    {
        return MapperService.getInstance().map(role, RoleEntity.class);
    }

}
