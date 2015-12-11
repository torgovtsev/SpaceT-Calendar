package com.github.teamcalendar.frontend.component;

import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

@ViewScoped
@Component(value = "addRoleForGroupBean")
public class AddRoleForGroupBean
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
