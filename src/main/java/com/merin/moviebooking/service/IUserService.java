package com.merin.moviebooking.service;

import java.util.List;
import com.merin.moviebooking.entity.User;
import com.merin.moviebooking.exception.UserCreationException;
import com.merin.moviebooking.exception.UserNotFoundException;

public interface IUserService 
{
	public void registerCustomer(User user) throws UserCreationException; 

	public List<User> getAllUsers() throws UserNotFoundException; 

	public void removeUser(int id) throws UserNotFoundException;

	public User getUserDetailsById(int id) throws UserNotFoundException;

	public boolean checkForExistance(String uName);

	
}
