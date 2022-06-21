package com.merin.moviebooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merin.moviebooking.entity.Login;
import com.merin.moviebooking.entity.User;
import com.merin.moviebooking.repository.IUserRepository;

@Service
public class ILoginServiceImpl implements ILoginService
{
	@Autowired
	IUserRepository iUserRepository;
	
	private Login logInfo=new Login();

	@Override
	public Login loginWithCredentials(String username, String password) 
	{
		 User user=iUserRepository.fetchDataForLogin(username);
		if(user.getPassword().equals(password))
		{
		logInfo.setLoginStatus(true);
		logInfo.setUser(user);
		}
		else
		{
			logInfo.setLoginStatus(false);
			logInfo.setUser(null);
		}
		return logInfo;
		
	}

	
	@Override
	public Login logoutCurrentUser() 
	{
		if(logInfo.isLoginStatus())
		{
			logInfo.setLoginStatus(false);
		}
		return logInfo;
		
	}
	
	

	@Override
	public boolean loginStatus() 
	{
		
		return logInfo.isLoginStatus();
	}

	@Override
	public String getCurrentUserRole() 
	{
		
		return logInfo.getCurrentUserRole();
	}

}
