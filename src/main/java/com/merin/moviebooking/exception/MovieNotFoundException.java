package com.merin.moviebooking.exception;

@SuppressWarnings("serial")
public class MovieNotFoundException extends Exception
{
	
	public String message;
	
	public MovieNotFoundException(String message) 
	{
		super(message);
	}
	
	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}

}
