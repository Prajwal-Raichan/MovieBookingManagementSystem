package com.merin.moviebooking.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merin.moviebooking.entity.User;
import com.merin.moviebooking.exception.UserCreationException;
import com.merin.moviebooking.exception.UserNotFoundException;
import com.merin.moviebooking.repository.IUserRepository;
import com.merin.moviebooking.validator.IResourceValidator;

@Service
public class IUserServiceImpl implements IUserService
{

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IResourceValidator iResourceValidator;


	@Override
	public void registerCustomer(User user) throws UserCreationException 
	{
		if(!iResourceValidator.usernameValidator(user.getUserName()))
			throw new UserCreationException("Enter Valid User Name");
		if(!iResourceValidator.passwordValidator(user.getPassword()))
			throw new UserCreationException("Enter The Valid Password Format (With 1-UpperCase, 1-LoweCase, 1-SpecialCharacter, 1-Number with minimum length 5");
		if(!iResourceValidator.nameValidator(user.getCustomerName()))
			throw new UserCreationException("Enter The Valid Customer Name With More Than 3 Characters");
		if(!iResourceValidator.contactValidator(user.getContactNumber()))
			throw new UserCreationException("Enter The Valid 10 Digit Mobile Number");
		if(!iResourceValidator.emailValidator(user.getEmailId()))
			throw new UserCreationException("Enter Valid Email ID");
			userRepository.saveAndFlush(user);
	}


	@Override
	public boolean checkForExistance(String uName) 
	{
		boolean userResult=userRepository.existsByUserName(uName);
		if(!userResult)
			return true;
		else  
			return false;
	}


	@Override
	public User getUserDetailsById(int id) throws UserNotFoundException
	{
		if(!userRepository.existsById(id))           //PresentData:is it False?==Fetching...[True or False]
			throw new UserNotFoundException("No User Exists With the User Id:"+id);
		
		return userRepository.findById(id).get();
 
	}

	@Override
	public List<User> getAllUsers() throws UserNotFoundException
	{
		List<User> userBean=userRepository.findAll();
		if(userBean.size()==0)
			throw new UserNotFoundException("No User Exists in Database");
		
		return userRepository.findAll();
	} 


	@Override
	public void removeUser(int id) throws UserNotFoundException
	{
		if(!userRepository.existsById(id))           //PresentData:is it False?==Fetching...[True or False]
			throw new UserNotFoundException("No User Exists With the User Id:"+id);
		
		userRepository.deleteById(id);

	}



}
