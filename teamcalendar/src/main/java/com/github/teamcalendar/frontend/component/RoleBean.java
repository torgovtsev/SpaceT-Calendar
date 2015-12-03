package com.github.teamcalendar.frontend.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.teamcalendar.middleware.dto.Permission;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.services.PermissionService;
import com.github.teamcalendar.middleware.services.RoleService;

@ManagedBean
@Component(value = "roleBean")
public class RoleBean
{
    @Autowired
    RoleService                 roleService;

    @Autowired
    PermissionService           permissionService;

    private Role                role       = new Role();

    private Map<String, String> permission = new HashMap<String, String>();

    private String              select;

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public String newRole()
    {
        this.role = new Role();
        return "addRole?faces-redirect=true";
    }

    public List<Role> getAllRole()
    {
        try
        {
            List<Role> role = roleService.getAllRoles();
            return role;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public String create()
    {
        roleService.addRole(role);
        return "RoleList?faces-redirect=true";
    }

    public String editRole()
    {
        Integer editId = this.role.getId();
        this.role = roleService.getRoleById(editId);

        return "editRole?faces-redirect=true";
    }

    public String deleteRole()
    {
        Integer editId = this.role.getId();
        this.role = roleService.getRoleById(editId);

        return "deleteRole?faces-redirect=true";
    }

    public String update()
    {
        roleService.updateRole(role);
        return "RoleList?faces-redirect=true";
    }

    public String delete()
    {
        roleService.deleteRole(role);
        return "RoleList?faces-redirect=true";
    }

    public String getSelect()
    {
        return select;
    }

    public void setSelect(String select)
    {
        this.select = select;
    }

    public Map<String, String> getAllPermission()
    {
        List<Permission> perm = permissionService.getAllPermissions();
        for (Permission permRole : perm)
        {
            permission.put(permRole.getName(), permRole.getName());
        }
        return permission;
    }

    public String addPermissionForRole()
    {
        Permission perm = permissionService.getPermissionByName(select);
        role.getPermissionRole().add(perm);
        roleService.updateRole(role);
        return "RoleList?faces-redirect=true";
    }

    public void deletePermission(Permission permission)
    {
        role.getPermissionRole().remove(permission);
        roleService.updateRole(role);

    }

}
