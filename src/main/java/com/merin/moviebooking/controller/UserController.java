package com.merin.moviebooking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.merin.moviebooking.entity.User;
import com.merin.moviebooking.exception.UserCreationException;
import com.merin.moviebooking.exception.UserNotFoundException;
import com.merin.moviebooking.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "User Controller",description = "User Management Portal")
public class UserController 
{
	Logger logger=LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;
	
	
	/** 
	 * @param user
	 * @return CREATED/FORBIDDEN
	 * @throws UserCreationException
	 * @apiNote User registers into the application 
	 */
	@PostMapping("/userRegistration")
	public HttpStatus registerUser(@RequestBody User user) throws UserCreationException
	{
	
		String uName=user.getUserName();
		boolean userExistance=userService.checkForExistance(uName);
		if(userExistance==false) 
		{
			logger.error("/---------- User Name Already Taken ----------/");
			return HttpStatus.FORBIDDEN;
		}
		else
		{
			
			userService.registerCustomer(user);
			logger.info("/---------- User Registration Completed Successfully ----------/");
			return HttpStatus.CREATED;
		}
		
	}
	
	
	/** 
	 * @param id
	 * @return user
	 * @throws UserNotFoundException
	 * @apiNote Admin views the User by Id
	 */
	@GetMapping("/viewUserById/{id}")
	public User getUserDetailsById(@PathVariable("id") int id) throws UserNotFoundException
	{
		
		logger.info("/---------- Fetched User Details Successfully ----------/");
		return userService.getUserDetailsById(id);
		
	}
	
	
	
	/** 
	 * @param 
	 * @return All Users
	 * @throws UserCreationException
	 * @apiNote Admin views All The Users
	 */
	@GetMapping("/viewsAllTheUsers") 
	public List<User> listAll() throws UserNotFoundException
	{
		
		logger.info("/---------- Fetched All the User Details Successfully ----------/");
		return userService.getAllUsers();
		
	}
	
	
	/** 
	 * @param id
	 * @return true
	 * @throws UserCreationException
	 * @apiNote Admin removes the user by Id
	 */
	@DeleteMapping("/deleteUserById/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") int id) throws UserNotFoundException
	{
		
		userService.removeUser(id);
		logger.info("/---------- Removed User Successfully ----------/");
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		
	}


}
