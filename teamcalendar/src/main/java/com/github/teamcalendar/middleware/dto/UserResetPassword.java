package com.github.teamcalendar.middleware.dto;

public class UserResetPassword
{
    private Integer id;
    private User    user_id;
    private String  resetUuid;

    public User getUser_id()
    {
        return user_id;
    }

    public void setUser_id(User user_id)
    {
        this.user_id = user_id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getResetUuid()
    {
        return resetUuid;
    }

    public void setResetUuid(String resetUuid)
    {
        this.resetUuid = resetUuid;
    }

    public UserResetPassword()
    {

    }

    public UserResetPassword(User user, String resetUuid)
    {
        this.user_id = user;
        this.resetUuid = resetUuid;
    }

}
