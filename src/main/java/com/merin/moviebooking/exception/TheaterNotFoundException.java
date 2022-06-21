package com.merin.moviebooking.exception;

@SuppressWarnings("serial")
public class TheaterNotFoundException extends Exception
{
	
	public String message;
	
	public TheaterNotFoundException(String message) 
	{
		super(message);
	}
	
	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}


}
