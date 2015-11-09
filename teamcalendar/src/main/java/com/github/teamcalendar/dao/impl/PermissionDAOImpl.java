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

import com.github.teamcalendar.dao.PermissionDAO;
import com.github.teamcalendar.domain.GroupEntity;
import com.github.teamcalendar.domain.PermissionEntity;
import com.github.teamcalendar.domain.RoleEntity;
import com.github.teamcalendar.domain.RolePermissionEntity;
import com.github.teamcalendar.middleware.dto.Group;
import com.github.teamcalendar.middleware.dto.Permission;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.utils.MapperService;

@Repository("permissionDAO")
public class PermissionDAOImpl extends AbstractDao<Integer, PermissionEntity> implements PermissionDAO
{

    private static final Logger LOG = LoggerFactory.getLogger(PermissionEntity.class);

    public void addPermission(Permission permission)
    {
        PermissionEntity data = convertPermissionToEntity(permission);
        persist(data);
    }

    public void updatePermission(Permission permission)
    {
        PermissionEntity data = convertPermissionToEntity(permission);
        update(data);
    }

    public void deletePermissione(Permission permission)
    {
        PermissionEntity data = convertPermissionToEntity(permission);
        delete(data);
    }

    @SuppressWarnings("unchecked")
    public List<Permission> getAllPermissions()
    {
        final List<Permission> result = new ArrayList<Permission>();

        Criteria criteria = createEntityCriteria();
        List<PermissionEntity> permissions = (List<PermissionEntity>)criteria.list();

        if (CollectionUtils.isEmpty(permissions))
        {
            LOG.error("NULL reference on users");
            return result;
        }

        for (PermissionEntity data : permissions)
        {
            Permission permission = convertEntityToPermission(data);
            result.add(permission);
        }

        return result;
    }

    public Permission getPermissionById(Integer id)
    {

        Permission result = null;

        try
        {
            PermissionEntity permission = getByKey(id);

            if (permission != null)
            {
                result = convertEntityToPermission(permission);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading user by id=%s", id), ex);
        }

        return result;
    }

    public Permission getPermissionByName(String name)
    {

        Permission result = null;

        try
        {
            Criteria crit = createEntityCriteria();
            crit.add(Restrictions.eq("name", name));
            PermissionEntity permission = (PermissionEntity)crit.uniqueResult();

            if (permission != null)
            {
                result = convertEntityToPermission(permission);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading permission by name=%s", name), ex);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public Set<Permission> getPermissionsForRole(Role role)
    {
        String name = role.getName();
        String hql = "select p from PermissionEntity p join p.rolePermissionEntity rp join rp.role r where r.name = :name";
        Query query = createEntityQuery(hql).setParameter("name", name);
        Set<PermissionEntity> permissions = (Set<PermissionEntity>)query.list();

        Set<Permission> result = new HashSet<Permission>();

        for (PermissionEntity per : permissions)
        {
            Permission data = convertEntityToPermission(per);
            result.add(data);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public void updatePermissionsForRole(Role role, List<Permission> rolePermissions)
    {

        //		RoleEntity roleEntity = = MapperService.getInstance().map(role, RoleEntity.class);
        //		
        //		String hql = "select rp from rolePermissionEntity rp where rp.role = :role";
        //		Query query = createEntityQuery(hql)..setParameter("role", roleEntity);
        //		List<RolePermissionEntity> oldPermission = (List<RolePermissionEntity>) query.list();	
        //		
        //		for(RolePermissionEntity perm: oldPermission){
        //			delete(perm);
        //		}
        //		
        //		Set<RolePermissionEntity> resultRolePermissionEntity = new HashSet<RolePermissionEntity>();
        //		for(Permission p: rolePermissions){
        //			PermissionEntity permissionEntity = MapperService.getInstance().map(p, PermissionEntity.class);
        //			RolePermissionEntity rolePermission = new RolePermissionEntity(permissionEntity, roleEntity);
        //			save(rolePermission);
        //			resultRolePermissionEntity.add(rolePermission);
        //		}
        //		roleEntity.setRolePermissionEntity(resultRolePermissionEntity);
        //		
    }

    private Permission convertEntityToPermission(PermissionEntity entity)
    {
        return MapperService.getInstance().map(entity, Permission.class);
    }

    private PermissionEntity convertPermissionToEntity(Permission permission)
    {
        return MapperService.getInstance().map(permission, PermissionEntity.class);
    }

}
