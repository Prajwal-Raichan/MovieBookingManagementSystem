package com.merin.moviebooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Login 
{
	
	private boolean loginStatus;
	
	@JsonIgnore
	private User user;        //Class Type Reference
	
	
	public Login() 
	{
		super();
	}


	public Login(boolean loginStatus, User user) {
		super();
		this.loginStatus = loginStatus;
		this.user = user;
	}


	public boolean isLoginStatus() {
		return loginStatus;
	}


	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@JsonIgnore
	public String getCurrentUserRole() 
	{
		if(user==null)
		{
		setUser(new User(null, null, "bot", null));
		}
		return user.getRole();
	}

	
}
