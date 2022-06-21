package com.merin.moviebooking.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.merin.moviebooking.entity.Seat;
import com.merin.moviebooking.exception.SeatNotFoundException;
import com.merin.moviebooking.exception.TicketNotFoundException;
import com.merin.moviebooking.service.ISeatService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/seat")
@Tag(name = "Seat Controller",description = "Seat Management Portal")
public class SeatController 
{
	
	Logger logger=LoggerFactory.getLogger(SeatController.class);
	
	@Autowired
	private ISeatService seatService;
	
	
	
	/** Admin adds the Theater **/
	@PostMapping("/addSeat")
	public HttpStatus insertSeat(@RequestBody Seat seat) throws  SeatNotFoundException
	{
		
		logger.info("/---------- Seat Added Successfully ----------/");
		seatService.addSeat(seat);
		return HttpStatus.CREATED;
		
	}
	
	/** Admin updates the theater **/
	@PutMapping("/update/{seatId}")
	public ResponseEntity<Boolean> modifySeat(@RequestBody Seat seat, @PathVariable("seatId") Integer seatId) throws SeatNotFoundException
	{
		
		seatService.updateSeat(seat,seatId);
		logger.info("/---------- Seat Updated Successfully ----------/");
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
	}
	
	
	/** Customer books the Seat **/
	@PutMapping("/bookSeat/{ticketId}/{seatId}")
	public ResponseEntity<Boolean> bookTheSeat(@RequestBody Seat seat,@PathVariable("ticketId")Integer ticketId, @PathVariable("seatId") Integer seatId) throws SeatNotFoundException, TicketNotFoundException
	{
		
		seatService.bookSeat(seat,ticketId,seatId);
		logger.info("/---------- Seat Booked Successfully ----------/");
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		
	}
	
	
	/** Customer cancels the Seat**/
	@PutMapping("/cancelSeat/{ticketId}/{seatId}") 
	public ResponseEntity<Boolean> cancelTheSeat(@RequestBody Seat seat,@PathVariable("ticketId")Integer ticketId, @PathVariable("seatId")Integer seatId) throws SeatNotFoundException, TicketNotFoundException
	{
		
		logger.info("/---------- Seat Cancelled Successfully ----------/");
		seatService.cancelSeatBooking(seat,ticketId,seatId);
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		
	}
	
	
	
	/** Admin blocks the Seat **/
	@PutMapping("/blockSeat/{seatId}")
	public ResponseEntity<Boolean> blockTheSeat(@RequestBody Seat seat,@PathVariable("seatId") Integer seatId) throws SeatNotFoundException
	{
		
		logger.info("/---------- Seat Blocked Successfully ----------/");
		seatService.blockSeat(seat,seatId);
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		
	}
	
	
	/** Customer views all the Seats**/
	@GetMapping("/viewAllTheSeats")
	public List<Seat> getAllSeatDetails() throws SeatNotFoundException
	{
		
		logger.info("/---------- Fetched All Seats Successfully ----------/");
		return seatService.getAllSeatDetails();
		
		
	}
	
	
	
}
