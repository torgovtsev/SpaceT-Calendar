package com.github.teamcalendar.frontend.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.teamcalendar.middleware.dto.Group;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.GroupService;
import com.github.teamcalendar.middleware.services.RoleService;
import com.github.teamcalendar.middleware.services.UsersService;

@ManagedBean
@Component(value = "groupBean")
public class GroupBean
{
    @Autowired
    GroupService                groupService;

    @Autowired
    UsersService                userService;

    @Autowired
    RoleService                 roleService;

    private Group               group = new Group();

    private Map<String, String> user  = new HashMap<String, String>();

    private Map<String, String> role  = new HashMap<String, String>();

    private String              select;

    public Group getGroup()
    {
        return group;
    }

    public void setGroup(Group group)
    {
        this.group = group;
    }

    public String newGroup()
    {
        this.group = new Group();
        return "addGroup?faces-redirect=true";
    }

    public String editGroup()
    {
        Integer editId = this.group.getId();
        this.group = groupService.getGroupById(editId);

        return "editGroup?faces-redirect=true";
    }
    
    public String editGroupRole()
    {
        Integer editId = this.group.getId();
        this.group = groupService.getGroupById(editId);
        
        return "editGroupRole?faces-redirect=true";
    }

    public String deleteGroup()
    {
        Integer editId = this.group.getId();
        this.group = groupService.getGroupById(editId);

        return "deleteGroup?faces-redirect=true";
    }

    public List<Group> getAllGroup()
    {
        try
        {
            List<Group> group = groupService.getAllGroups();
            return group;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public String create()
    {
        groupService.addGroup(group);
        return "GroupList?faces-redirect=true";
    }

    public String update()
    {
        groupService.updateGroup(group);
        return "GroupList?faces-redirect=true";
    }

    public String delete()
    {
        groupService.deleteGroup(group);
        return "GroupList?faces-redirect=true";
    }

    public String getSelect()
    {
        return select;
    }

    public void setSelect(String select)
    {
        this.select = select;
    }

    public Map<String, String> getAllUser()
    {
        List<User> us = userService.getAllUsers();
        for (User userName : us)
        {
            user.put(userName.getEmail(), userName.getEmail());
        }
        return user;
    }

    public String addUserInGroup()
    {
        User user = userService.getUserByEmail(select);
        group.getUserGroup().add(user);
        groupService.updateGroup(group);
        return "GroupList?faces-redirect=true";
    }

    public Map<String, String> getAllRole()
    {
        List<Role> ro = roleService.getAllRoles();
        for (Role roleName : ro)
        {
            role.put(roleName.getName(), roleName.getName());
        }
        return role;
    }

    public String addRoleForGroup()
    {
        Role role = roleService.getRoleByName(select);
        group.getRoleGroup().add(role);
        groupService.updateGroup(group);
        return "GroupList?faces-redirect=true";
    }

    public void deleteUser(User user)
    {
        group.getUserGroup().remove(user);
        groupService.updateGroup(group);

    }

    public void deleteRole(Role role)
    {
        group.getRoleGroup().remove(role);
        groupService.updateGroup(group);

    }

}
