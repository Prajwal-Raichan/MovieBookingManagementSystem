package com.merin.moviebooking.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception
{
	public String message;
	
	
	public UserNotFoundException(String message) 
	{
		super(message);
	}
	
	
	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}

}
