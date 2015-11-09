package com.github.teamcalendar.middleware.dto;

public class Question {

	private Integer id;
	private String questiontext;
	
	public Question() {
		
	}
	
	public Question(Integer id, String text) {
		this.id = id;
		this.setQuestiontext(text);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestiontext() {
		return questiontext;
	}

	public void setQuestiontext(String questiontext) {
		this.questiontext = questiontext;
	}
	
}
