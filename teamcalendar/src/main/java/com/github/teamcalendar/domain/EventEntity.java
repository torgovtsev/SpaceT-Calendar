package com.github.teamcalendar.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.dozer.Mapping;


@Entity
@Table(name = "EVENT")
public class EventEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    @Mapping("id")
	private Integer id;
    
    @OneToOne
    @JoinColumn(name = "EVENT_TYPE")
    @Mapping("eventType")
    private EventTypeEntity eventType;
    
    @ManyToOne
    @JoinColumn(name="USER_ID")
    @Mapping("user")
    private UserEntity user; //es ist many-to-one Verhaeltnis
    
    @OneToOne
    @JoinColumn(name = "INFO_ID")
    @Mapping("info")
    private InformationEntity information;
    
    @Column(name = "DATE", nullable = false)
    @Mapping("date")
    private Date date;
    

	public EventEntity() {
    	
    }
    
    public  EventEntity(EventTypeEntity etent, UserEntity uent, InformationEntity ient, Date date) {
    	this.eventType = etent;
    	this.user = uent;
    	this.information = ient;
    	this.date = date;
    }
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EventTypeEntity getEventType() {
		return eventType;
	}

	public void setEventType(EventTypeEntity eventType) {
		this.eventType = eventType;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public InformationEntity getInformation() {
		return information;
	}

	public void setInformation(InformationEntity information) {
		this.information = information;
	}
}
