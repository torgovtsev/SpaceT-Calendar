package com.github.teamcalendar.frontend.component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

@ManagedBean
@ViewScoped
@Component(value = "addUserForGroupBean")
public class AddUserForGroupBean
{

    private String select;

    public String getSelect()
    {
        return select;
    }

    public void setSelect(String select)
    {
        this.select = select;
    }

}
