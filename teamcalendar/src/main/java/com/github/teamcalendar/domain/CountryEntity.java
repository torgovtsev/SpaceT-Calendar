package com.github.teamcalendar.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.github.teamcalendar.domain.UserEntity;

@Entity
@Table(name = "COUNTRY", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class CountryEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer         id;

    @Column(name = "NAME", unique = true, nullable = false, length = 128)
    private String          name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private Set<UserEntity> userEntity = new HashSet<UserEntity>(0);

    public CountryEntity()
    {
    }

    public CountryEntity(String name)
    {
        this.name = name;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<UserEntity> getUserEntity()
    {
        return this.userEntity;
    }

    public void setUserEntity(Set<UserEntity> userEntity)
    {
        this.userEntity = userEntity;
    }

}
