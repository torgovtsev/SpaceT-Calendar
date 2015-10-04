package com.github.teamcalendar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "main")
@RequestScoped
public class Main {
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@PostConstruct
	private void init()
	{
		msg = "Hello World! I'm Teamcalendar";
	}
}
