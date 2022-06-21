package com.merin.moviebooking.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merin.moviebooking.entity.Ticket;
import com.merin.moviebooking.exception.TicketNotFoundException;
import com.merin.moviebooking.repository.ITicketRepository;

@Service
public class ITicketServiceImpl implements ITicketService   //Status thing has to be resolved
{

	@Autowired
	private ITicketRepository ticketRepository;
	
	@Override
	public void addTicket(Ticket ticket) throws TicketNotFoundException
	{
		if(ticketRepository.existsById(ticket.getTicketId()))
			throw new TicketNotFoundException("Ticket Already Exists");
		
		ticketRepository.saveAndFlush(ticket);
		
	}

	@Override
	public Ticket getTicketDetailsById(int id) throws TicketNotFoundException
	{
		if(!ticketRepository.existsById(id))
			throw new TicketNotFoundException("Ticket Already Exists");
		
		return ticketRepository.findById(id).get();
	}

	@Override
	public List<Ticket> getAllTheTicketDetails() throws TicketNotFoundException
	{
		List<Ticket> ticketBean=ticketRepository.findAll();
		if(ticketBean.size()==0)
			throw new TicketNotFoundException("No Ticket Exists");
		
		return ticketRepository.findAll();
		
	}

	@Override
	public void deleteTicketById(int id) throws TicketNotFoundException
	{
		if(!ticketRepository.existsById(id))
			throw new TicketNotFoundException("Ticket Doesn't Exists");
		ticketRepository.deleteById(id);
		
	}

}
