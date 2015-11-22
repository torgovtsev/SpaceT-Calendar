package com.github.teamcalendar.middleware.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User
{

    private Integer    id;
    private Country    country;
    private String     firstName;
    private String     lastName;

    private String     email;
    private String     password;
    private String     secretQuestion;
    private String     secretAnswer;
    private String     mobile;
    private Boolean    sex;
    private int        age;
    private Date       registrationDate;
    private Boolean    isBlocked;
    private Boolean    isDeleted;
    private Boolean    isVerified;

    private Set<Group> groupUser = new HashSet<Group>();

    private Set<Role>  roleUser  = new HashSet<Role>(0);

    public User()
    {

    }

    public User(String password, String firstName, String secondName, String secretQuestion, String secretAnswer, String mobile,
            Integer age, String Email, Boolean sex, Country country)
    {

        this.country = country;
        this.firstName = firstName;
        this.lastName = secondName;
        this.email = Email;
        this.password = password;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
        this.mobile = mobile;
        this.sex = sex;
        this.age = age;
        this.registrationDate = new Date();
        this.isBlocked = false;
        this.isDeleted = false;
        this.isVerified = false;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Country getCountryEntity()
    {
        return country;
    }

    public void setCountryEntity(Country country)
    {
        this.country = country;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSecretQuestion()
    {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion)
    {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer()
    {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer)
    {
        this.secretAnswer = secretAnswer;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public Boolean getSex()
    {
        return sex;
    }

    public void setSex(Boolean sex)
    {
        this.sex = sex;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public Date getRegistrationDate()
    {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate)
    {
        this.registrationDate = registrationDate;
    }

    public Boolean getIsBlocked()
    {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked)
    {
        this.isBlocked = isBlocked;
    }

    public Boolean getIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsVerified()
    {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified)
    {
        this.isVerified = isVerified;
    }

    public Country getCountry()
    {
        return country;
    }

    public void setCountry(Country country)
    {
        this.country = country;
    }

    public Set<Group> getGroupUser()
    {
        return groupUser;
    }

    public void setGroupUser(Set<Group> groupUser)
    {
        this.groupUser = groupUser;
    }

    public Set<Role> getRoleUser()
    {
        return roleUser;
    }

    public void setRoleUser(Set<Role> roleUser)
    {
        this.roleUser = roleUser;
    }

}
