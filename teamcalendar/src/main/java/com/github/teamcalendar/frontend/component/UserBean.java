package com.github.teamcalendar.frontend.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.teamcalendar.middleware.dto.Country;
import com.github.teamcalendar.middleware.dto.Question;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.CountryService;
import com.github.teamcalendar.middleware.services.QuestionService;
import com.github.teamcalendar.middleware.services.RoleService;
import com.github.teamcalendar.middleware.services.UsersService;

@ManagedBean
@Component(value = "userBean")
public class UserBean
{
    @Autowired
    private UsersService        userService;

    @Autowired
    private RoleService         roleService;

    @Autowired
    private CountryService      countryService;

    @Autowired
    private QuestionService     questionService;

    private User                user      = new User();

    private Map<String, String> roles     = new HashMap<String, String>();

    private Map<String, String> countries = new HashMap<String, String>();

    private Map<String, String> questions = new HashMap<String, String>();

    private String              select;

    private String              selectQuestion;

    private String              selectRole;

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String newUser()
    {
        this.user = new User();
        return "addUser.xhtml?faces-redirect=true";
    }

    /**
     * Prepare User for edit
     * 
     * @return redirect to editUser page
     */
    public String editUser()
    {
        Integer editId = this.user.getId();
        this.user = userService.getUserByID(editId);

        return "editUser?faces-redirect=true";
    }

    public String delete()
    {
        userService.deleteUser(user);
        return "UserList?faces-redirect=true";
    }

    public String cancel()
    {
        return "UserList?faces-redirect=true";
    }

    public List<User> getAllUser()
    {
        try
        {
            List<User> user = userService.getAllUsers();
            return user;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Add new User
     * 
     * @return if success validate redirect to userList
     */
    public void create()
    {
        FacesContext context = FacesContext.getCurrentInstance();

        if (userService.validateUser(user))
        {
            Country country = countryService.getCountryByName(select);
            user.setCountryEntity(country);
            user.setRegistrationDate(new java.util.Date());
            user.setSecretQuestion(selectQuestion);
            user.setIsBlocked(false);
            user.setIsDeleted(false);
            user.setIsVerified(true);
            userService.addUser(user);
            try
            {
                context.getExternalContext().redirect("UserList.xhtml");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

    }

    public String update()
    {
        userService.updateUser(user);
        return "UserList?faces-redirect=true";
    }

    public String updateBlocked()
    {
        //send email
        userService.updateUser(user);
        return "UserList?faces-redirect=true";
    }

    public String updateDeleted()
    {
        //send email
        userService.updateUser(user);
        return "UserList?faces-redirect=true";
    }

    public Map<String, String> getAllRole()
    {
        List<Role> rol = roleService.getAllRoles();
        for (Role roleName : rol)
        {
            roles.put(roleName.getName(), roleName.getDescription());
        }
        return roles;
    }

    public String addRoleForUser()
    {
        Role role = roleService.getRoleByName(selectRole);
        user.getRoleUser().add(role);
        userService.updateUser(user);
        return "editUser?faces-redirect=true";
    }

    public void deleteRole(Role role)
    {
        user.getRoleUser().remove(role);
        userService.updateUser(user);
    }

    public Map<String, String> getAllCountry()
    {

        List<Country> country = countryService.getAllCountries();
        for (Country countryName : country)
        {
            countries.put(countryName.getName(), countryName.getName());
        }
        return countries;
    }

    public Map<String, String> getAllQuestion()
    {

        List<Question> question = questionService.getAllQestions();
        for (Question questionText : question)
        {
            questions.put(questionText.getQuestiontext(), questionText.getQuestiontext());
        }
        return questions;
    }

    public String getSelect()
    {
        return select;
    }

    public void setSelect(String select)
    {
        this.select = select;
    }

    public String getSelectQuestion()
    {
        return selectQuestion;
    }

    public void setSelectQuestion(String selectQuestion)
    {
        this.selectQuestion = selectQuestion;
    }

    public String getSelectRole()
    {
        return selectRole;
    }

    public void setSelectRole(String selectRole)
    {
        this.selectRole = selectRole;
    }

}
