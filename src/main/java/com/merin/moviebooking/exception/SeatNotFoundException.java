package com.merin.moviebooking.exception;

@SuppressWarnings("serial")
public class SeatNotFoundException extends Exception
{
	
	public String message;
	
	public SeatNotFoundException(String message) 
	{
		super(message);
	}

	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}
	
}
