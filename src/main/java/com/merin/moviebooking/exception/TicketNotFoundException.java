package com.merin.moviebooking.exception;

@SuppressWarnings("serial")
public class TicketNotFoundException extends Exception
{

	public String message;

	public TicketNotFoundException(String message) 
	{
		super(message);
	}

	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}
}
