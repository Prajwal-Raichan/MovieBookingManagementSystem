package com.merin.moviebooking.exception;

@SuppressWarnings("serial")
public class ShowNotFoundException extends Exception
{
	public String message;
	
	public ShowNotFoundException(String message) 
	{
		super(message);
	}
	
	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}
	

}
