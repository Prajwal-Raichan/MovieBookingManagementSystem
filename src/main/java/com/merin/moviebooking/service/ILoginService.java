package com.merin.moviebooking.service;

import com.merin.moviebooking.entity.Login;

public interface ILoginService 
{

	public Login loginWithCredentials(String username, String password);

	public Login logoutCurrentUser();

	public boolean loginStatus();

	public String getCurrentUserRole();
	

}
