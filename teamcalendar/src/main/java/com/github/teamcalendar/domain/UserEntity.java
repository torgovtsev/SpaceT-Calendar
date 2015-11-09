package com.github.teamcalendar.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "USER", uniqueConstraints = { @UniqueConstraint(columnNames = "EMAIL"), @UniqueConstraint(columnNames = "MOBILE") })
public class UserEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer              id;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    private CountryEntity        countryEntity;

    @Column(name = "FIRST_NAME", nullable = false, length = 64)
    private String               firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 128)
    private String               lastName;

    @Column(name = "EMAIL", unique = true, nullable = false, length = 128)
    private String               email;

    @Column(name = "PASSWORD", nullable = false, length = 1024)
    private String               password;

    @Column(name = "SECRET_QUESTION", nullable = false, length = 128)
    private String               secretQuestion;

    @Column(name = "SECRET_ANSWER", nullable = false, length = 1024)
    private String               secretAnswer;

    @Column(name = "MOBILE", unique = true, nullable = false, length = 48)
    private String               mobile;

    @Column(name = "SEX")
    private Boolean              sex;

    @Column(name = "AGE", nullable = false)
    private int                  age;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGISTRATION_DATE", length = 26)
    private Date                 registrationDate;

    @Column(name = "IS_BLOCKED", nullable = false)
    private boolean              isBlocked;

    @Column(name = "IS_DELETED", nullable = false)
    private boolean              isDeleted;

    @Column(name = "IS_VERIFIED", nullable = false)
    private boolean              isVerified;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRoleEntity>  userRoleEntity  = new HashSet<UserRoleEntity>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserGroupEntity> userGroupEntity = new HashSet<UserGroupEntity>(0);

    public UserEntity()
    {
    }

    public UserEntity(CountryEntity countryEntity, String firstName, String lastName, String email, String password, String secretQuestion,
            String secretAnswer, String mobile, Boolean sex, int age, boolean isBlocked, boolean isDeleted, boolean isVerified)
    {
        this.countryEntity = countryEntity;

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
        this.mobile = mobile;
        this.sex = sex;
        this.age = age;
        this.isBlocked = isBlocked;
        this.isDeleted = isDeleted;
        this.isVerified = isVerified;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public CountryEntity getCountryEntity()
    {
        return this.countryEntity;
    }

    public void setCountryEntity(CountryEntity countryEntity)
    {
        this.countryEntity = countryEntity;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSecretQuestion()
    {
        return this.secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion)
    {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer()
    {
        return this.secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer)
    {
        this.secretAnswer = secretAnswer;
    }

    public String getMobile()
    {
        return this.mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public Boolean getSex()
    {
        return this.sex;
    }

    public void setSex(Boolean sex)
    {
        this.sex = sex;
    }

    public int getAge()
    {
        return this.age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public Date getRegistrationDate()
    {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate)
    {
        this.registrationDate = registrationDate;
    }

    public boolean getIsBlocked()
    {
        return this.isBlocked;
    }

    public void setIsBlocked(boolean isBlocked)
    {
        this.isBlocked = isBlocked;
    }

    public boolean getIsDeleted()
    {
        return this.isDeleted;
    }

    public void setIsDeleted(boolean isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public boolean getIsVerified()
    {
        return this.isVerified;
    }

    public void setIsVerified(boolean isVerified)
    {
        this.isVerified = isVerified;
    }

    public Set<UserRoleEntity> getUserRoleEntity()
    {
        return this.userRoleEntity;
    }

    public void setUserRoleEntity(Set<UserRoleEntity> userRoleEntity)
    {
        this.userRoleEntity = userRoleEntity;
    }

    public Set<UserGroupEntity> getUserGroupEntity()
    {
        return this.userGroupEntity;
    }

    public void setUserGroupEntity(Set<UserGroupEntity> userGroupEntity)
    {
        this.userGroupEntity = userGroupEntity;
    }

}
