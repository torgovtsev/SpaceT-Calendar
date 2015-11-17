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
        boolean loggedIn = false;

        if (username != null && password != null)
        {
            if (username.contains("@") && username.contains("."))
            {
                User user = userService.getUserByEmail(username);
                if (user != null)
                {
                    if (user.getPassword().equals(password))
                    {
                        loggedIn = true;
                        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        context.addCallbackParam("loggedIn", loggedIn);
                    }
                    else
                    {
                        loggedIn = false;
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Loggin Error", "Incorrect email or password");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                }
                else
                {
                    loggedIn = false;
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Loggin Error", "No such user");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        }

    }
}
