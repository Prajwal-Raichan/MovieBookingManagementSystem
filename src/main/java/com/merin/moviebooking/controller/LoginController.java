package com.merin.moviebooking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.merin.moviebooking.entity.Login;
import com.merin.moviebooking.service.ILoginService;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping()
@Tag(name = "Login Controller",description = "Login Management Portal")
public class LoginController 
{
	Logger logger=LoggerFactory.getLogger(LoginController.class);
	  
	
	@Autowired
	ILoginService iLoginService;
	
	Login login=new Login();  //Global Object
	
	/** User Logins In with Username and Password **/
	@PostMapping("/login/{username}/{password}")
	public HttpStatus userLogin(@PathVariable("username") String username,@PathVariable("password") String password) 
	{
		
		
		login=iLoginService.loginWithCredentials(username,password);
		if(!login.isLoginStatus())
		{
			logger.error("/---------- Login Failure ----------/");
			return HttpStatus.FORBIDDEN;
		}
		else
		{
		logger.info("/---------- Login Successfull ----------/");
		return HttpStatus.ACCEPTED;
		}
		
	}
	
	/** User Logins Out **/
	@PostMapping("/logout")                  
	public HttpStatus logoutUser()
	{
		if(this.loginStatus())
		iLoginService.logoutCurrentUser();
		return HttpStatus.ACCEPTED;
		
	}
	
	
	@GetMapping("/loginStatus")
	public boolean loginStatus()
	{
		return iLoginService.loginStatus();
		
	}
	
	
	@GetMapping("/getCurrentUserRole")
	public String getCurrentUserRole()
	{
		return iLoginService.getCurrentUserRole();
		
	}
	
	
	
	
	
	

}
