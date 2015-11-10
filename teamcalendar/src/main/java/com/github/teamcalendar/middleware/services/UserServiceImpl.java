package com.github.teamcalendar.middleware.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.teamcalendar.middleware.dto.Country;
import com.github.teamcalendar.middleware.dto.Group;
import com.github.teamcalendar.middleware.dto.Permission;
import com.github.teamcalendar.middleware.dto.Question;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.dto.User;

@Service("userService")
public class UserServiceImpl implements UserService
{

    private static final String HELLO_WORLD = "Hello world";

    @Autowired
    private QuestionService     questionService;

    @Autowired
    private PermissionService   permissionService;

    @Autowired
    private RoleService         roleService;

    @Autowired
    private GroupService        groupService;

    @Autowired
    private CountryService      countryService;

    @Autowired
    private UsersService        userService;

    @Override
    public String getName()
    {
        List<Question> question = questionService.getAllQestions();
        for (Question q : question)
        {
            System.out.println(q.getQuestiontext());
        }

        List<Permission> permission = permissionService.getAllPermissions();
        for (Permission q : permission)
        {
            System.out.println(q.getName());
        }

        List<Role> role = roleService.getAllRoles();
        for (Role q : role)
        {
            System.out.println(q.getName());
        }

        List<Group> group = groupService.getAllGroups();
        for (Group q : group)
        {
            System.out.println(q.getName());
        }

        List<Country> country = countryService.getAllCountries();
        for (Country q : country)
        {
            System.out.println(q.getName());
        }

        List<User> user = userService.getAllUsers();
        for (User q : user)
        {
            System.out.println(q.getFirstName());
        }

        //Question q = new Question();
        //q.setQuestiontext("Why don`t work Country?");
        //questionService.add(q);
        return HELLO_WORLD;
    }

}
