package com.merin.moviebooking.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.merin.moviebooking.entity.Ticket;
import com.merin.moviebooking.exception.TicketNotFoundException;
import com.merin.moviebooking.service.ITicketService;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/ticket")
@Tag(name = "Ticket Controller",description = "Ticket Management Portal")
public class TicketController 
{
	Logger logger=LoggerFactory.getLogger(TicketController.class);
	
	@Autowired
	private ITicketService ticketService;
	
	
	/** Customer adds the Ticket **/
	@PostMapping("/addTicket")
	public HttpStatus insertTicket(@RequestBody Ticket ticket) throws TicketNotFoundException
	{
		
	    ticketService.addTicket(ticket);
	    logger.info("/---------- Ticket Added Successfully ----------/");
	    return HttpStatus.CREATED;
		
	}
	
	/** Customer views the Ticket By ID **/
	@GetMapping("/viewTicketById/{id}")
	public Ticket getTicketDetails(@PathVariable("id") int id) throws TicketNotFoundException
	{
		
		logger.info("/---------- Fetched Ticket Details Successfully ----------/");
		return ticketService.getTicketDetailsById(id);
		
	}
	
	
	/** Customer views all his Ticket **/
	@GetMapping("/viewAllTheTickets")
	public List<Ticket> getAllTheTicketDetails() throws TicketNotFoundException
	{
		
		logger.info("/---------- Fetched All Ticket Details Successfully ----------/");
		return ticketService.getAllTheTicketDetails();
		
	}
	
	
	/** Customer deletes the Ticket By Id **/
	@DeleteMapping("/deleteTicketById/{id}") 
	public ResponseEntity<Boolean> removeTicketById(@PathVariable("id") int id) throws TicketNotFoundException 
	{
		
		ticketService.deleteTicketById(id);
		logger.info("/---------- Deleted Ticket Successfully ----------/");
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		
	}

}
