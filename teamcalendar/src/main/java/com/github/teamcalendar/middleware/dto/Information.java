package com.github.teamcalendar.middleware.dto;

public class Information
{
    private Integer id;
    private String  message;

    //private Information_Type infoType;

    public Information()
    {

    }

    public Information(String mes)
    {
        this.message = mes;
        //this.infoType = infType;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
    //	public Information_Type getInfoType() {
    //		return infoType;
    //	}
    //	public void setInfoType(Information_Type infoType) {
    //		this.infoType = infoType;
    //	}

}
