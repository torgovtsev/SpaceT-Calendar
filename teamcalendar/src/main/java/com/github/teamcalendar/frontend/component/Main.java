package com.github.teamcalendar.frontend.component;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.teamcalendar.middleware.services.UserService;

@Component(value = "main")
@RequestScoped
public class Main
{

    private String      msg;

    @Autowired
    private UserService userService;

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    @PostConstruct
    private void init()
    {
        msg = userService.getName();
    }
}