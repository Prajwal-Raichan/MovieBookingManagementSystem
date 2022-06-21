package com.merin.moviebooking.exception;

@SuppressWarnings("serial")
public class ScreenNotFoundException extends Exception
{

	public String message;

	public ScreenNotFoundException(String message) 
	{
		super(message);
	}

	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}

}
