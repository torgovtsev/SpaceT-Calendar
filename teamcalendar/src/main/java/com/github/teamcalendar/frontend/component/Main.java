package com.github.teamcalendar.frontend.component;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.UserService;
import com.github.teamcalendar.middleware.services.UsersService;

@Component(value = "main")
@RequestScoped
public class Main
{

    private List<User>      msg;

    @Autowired
    private UsersService userService;

    public List<User> getMsg()
    {
        return msg;
    }

    public void setMsg(List<User> msg)
    {
        this.msg = msg;
    }

    @PostConstruct
    private void init()
    {
        msg = userService.getAllUsers();
    }
}
