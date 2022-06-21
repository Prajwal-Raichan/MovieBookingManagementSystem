package com.merin.moviebooking.exception;

@SuppressWarnings("serial")
public class BookingNotFoundException extends Exception
{
	
	public String message;
	
	public BookingNotFoundException(String message) 
	{
		super(message);
	}
	
	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}

}
