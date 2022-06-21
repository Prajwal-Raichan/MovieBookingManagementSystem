package com.merin.moviebooking.exception;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends Exception
{
	
	public String message;
	
	public CustomerNotFoundException(String message) 
	{
		super(message);
	}
	
	
	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}

}
