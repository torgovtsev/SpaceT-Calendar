package com.github.teamcalendar.middleware.dto;

public class Event_Type {
	private Integer id;
	private String name;
	private String description;
	private String textColor;
	private String backColor;
	
	

	public Event_Type() {
		
	}
	
	public Event_Type(String name, String descr, String text, String back) {
		this.name = name;
		this.description = descr; 
		this.textColor = text;
		this.backColor = back;
	}
	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
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
