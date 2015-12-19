package com.github.teamcalendar.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.dozer.Mapping;

@Entity
@Table(name = "USER_", uniqueConstraints = { @UniqueConstraint(columnNames = "EMAIL"), @UniqueConstraint(columnNames = "MOBILE") })
public class UserEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    @Mapping("id")
    private Integer           id;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    @Mapping("country")
    private CountryEntity     countryEntity;

    @Column(name = "FIRST_NAME", nullable = false, length = 64)
    @Mapping("firstName")
    private String            firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 128)
    @Mapping("lastName")
    private String            lastName;

    @Column(name = "EMAIL", unique = true, nullable = false, length = 128)
    @Mapping("email")
    private String            email;

    @Column(name = "PASSWORD", nullable = false, length = 1024)
    @Mapping("password")
    private String            password;

    @Column(name = "SECRET_QUESTION", nullable = false, length = 128)
    @Mapping("secretQuestion")
    private String            secretQuestion;

    @Column(name = "SECRET_ANSWER", nullable = false, length = 1024)
    @Mapping("secretAnswer")
    private String            secretAnswer;

    @Column(name = "MOBILE", unique = true, nullable = false, length = 48)
    @Mapping("mobile")
    private String            mobile;

    @Column(name = "SEX")
    @Mapping("sex")
    private Boolean           sex;

    @Column(name = "AGE", nullable = false)
    @Mapping("age")
    private int               age;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGISTRATION_DATE", length = 26)
    @Mapping("registrationDate")
    private Date              registrationDate;

    @Column(name = "IS_BLOCKED", nullable = false)
    @Mapping("isBlocked")
    private boolean           isBlocked;

    @Column(name = "IS_DELETED", nullable = false)
    @Mapping("isDeleted")
    private boolean           isDeleted;

    @Column(name = "IS_VERIFIED", nullable = false)
    @Mapping("isVerified")
    private boolean           isVerified;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) })
    @Mapping("roleUser")
    private Set<RoleEntity>   roleUserEntity   = new HashSet<RoleEntity>(0);

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_Group", joinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "GROUP_ID", nullable = false, updatable = false) })
    @Mapping("groupUser")
    private Set<GroupEntity>  groupUserEntity  = new HashSet<GroupEntity>(0);
    
    @OneToMany(mappedBy="user")
    @Mapping("userEvent")
    private List<EventEntity> eventEntities = new ArrayList<EventEntity>(0);

	public List<EventEntity> getEventEntities() {
		return eventEntities;
	}


	public void setEventEntities(List<EventEntity> eventEntities) {
		this.eventEntities = eventEntities;
	}


	public UserEntity()
    {
    }
	

    public UserEntity(CountryEntity countryEntity, String firstName, String lastName, String email, String password, String secretQuestion,
            String secretAnswer, String mobile, Boolean sex, int age, boolean isBlocked, boolean isDeleted, boolean isVerified,
            Set<RoleEntity> roleUserEntity, Set<GroupEntity> groupUserEntity)
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
        this.roleUserEntity = roleUserEntity;
        this.groupUserEntity = groupUserEntity;
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

    public Set<RoleEntity> getRoleUserEntity()
    {
        return this.roleUserEntity;
    }

    public void setRoleUserEntity(Set<RoleEntity> roleEntity)
    {
        this.roleUserEntity = roleEntity;
    }

    public Set<GroupEntity> getGroupUserEntity()
    {
        return this.groupUserEntity;
    }

    public void setGroupUserEntity(Set<GroupEntity> groupEntity)
    {
        this.groupUserEntity = groupEntity;
    }

}
