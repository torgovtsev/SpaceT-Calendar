package com.github.teamcalendar.middleware.dto;

public class Event_Type {
	private Integer id;
	private String name;
	private String description;
	
	public Event_Type() {
		
	}
	
	public Event_Type(String name, String descr) {
		this.name = name;
		this.description = descr; 
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
