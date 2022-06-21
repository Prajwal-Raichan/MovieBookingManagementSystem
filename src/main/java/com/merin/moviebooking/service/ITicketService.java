package com.merin.moviebooking.service;

import java.util.List;
import com.merin.moviebooking.entity.Ticket;
import com.merin.moviebooking.exception.TicketNotFoundException;

public interface ITicketService 
{

	public void addTicket(Ticket ticket) throws TicketNotFoundException;

	public Ticket getTicketDetailsById(int id) throws TicketNotFoundException;

	public List<Ticket> getAllTheTicketDetails() throws TicketNotFoundException;

	public void deleteTicketById(int id) throws TicketNotFoundException;
	

}
