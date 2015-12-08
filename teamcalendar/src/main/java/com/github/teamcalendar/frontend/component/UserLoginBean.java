package com.github.teamcalendar.frontend.component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.UsersService;

@ManagedBean(name = "userLoginBean")
@Component(value = "userLoginBean")
@RequestScoped
public class UserLoginBean
{
    private final String STRING_FEEDBACK_GENERAL_ERROR      = "Something went wrong";
    private final String STRING_FEEDBACK_LOGGED_IN          = "Logged in";

    private final String STRING_FEEDBACK_LOGIN_MUSTBE_EMAIL = "Username should be email address!";
    private final String STRING_FEEDBACK_NO_SUCH_USER       = "No such user";

    private final String STRING_FEEDBACK_INCORRECT_USERNAME = "Incorrect email or password";

    @Autowired
    private UsersService userService;

    private String       username;
    private String       password;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void login(ActionEvent event)
    {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;

        if (username.contains("@") && username.contains("."))
        {
            User user = userService.getUserByEmail(username);
            if (user != null)
            {
                if (user.getPassword().equals(password))
                {
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, STRING_FEEDBACK_LOGGED_IN, username);
                    context.addCallbackParam("loggedIn", true);
                }
                else
                {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, STRING_FEEDBACK_GENERAL_ERROR,
                            STRING_FEEDBACK_INCORRECT_USERNAME);
                }
            }
            else
            {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, STRING_FEEDBACK_GENERAL_ERROR, STRING_FEEDBACK_NO_SUCH_USER);
            }
        }
        else
        {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, STRING_FEEDBACK_GENERAL_ERROR, STRING_FEEDBACK_LOGIN_MUSTBE_EMAIL);
        }

        FacesContext.getCurrentInstance().addMessage(null, message);

    }
}
