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
@Table(name = "EVENT_TYPE")
public class EventTypeEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    @Mapping("id")
    private Integer           id;

    @Column(name = "NAME", nullable = false, length = 100)
    @Mapping("name")
    private String            name;

    @Column(name = "description", unique = true, nullable = false, length = 400)
    @Mapping("description")
    private String            description;

    @Column(name = "TEXTCOLOR")
    @Mapping("textColor")
    private String            textColor;

    @Column(name = "BACKCOLOR")
    @Mapping("backColor")
    private String            backColor;

    public EventTypeEntity()
    {

    }

    public EventTypeEntity(String name, String descr, String text, String back)
    {
        this.name = name;
        this.description = descr;
        this.backColor = back;
        this.textColor = text;
    }

    public String getTextColor()
    {
        return textColor;
    }

    public void setTextColor(String textColor)
    {
        this.textColor = textColor;
    }

    public String getBackColor()
    {
        return backColor;
    }

    public void setBackColor(String backColor)
    {
        this.backColor = backColor;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
