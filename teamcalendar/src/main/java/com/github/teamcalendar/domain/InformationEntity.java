package com.github.teamcalendar.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dozer.Mapping;

@Entity
@Table(name = "INFORMATION")
public class InformationEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    @Mapping("id")
    private Integer           id;

    @Column(name = "MESSAGE", unique = true, nullable = false, length = 400)
    @Mapping("message")
    private String            message;

    //    @OneToOne
    //    @JoinColumn(name = "INFORMATION_TYPE_ID")
    //    @Mapping("infoType")
    //    private InformationTypeEntity informationtypeEntity;

    public InformationEntity()
    {

    }

    public InformationEntity(String mes)
    {
        this.message = mes;
        //this.informationtypeEntity = ent;
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

    //	public InformationTypeEntity getInformationtypeEntity() {
    //		return informationtypeEntity;
    //	}
    //
    //	public void setInformationtypeEntity(InformationTypeEntity informationtypeEntity) {
    //		this.informationtypeEntity = informationtypeEntity;
    //	}
}
