package com.merin.moviebooking.controller;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.merin.moviebooking.entity.Booking;
import com.merin.moviebooking.exception.BookingNotFoundException;
import com.merin.moviebooking.exception.CustomerNotFoundException;
import com.merin.moviebooking.exception.PaymentNotFoundException;
import com.merin.moviebooking.exception.ShowNotFoundException;
import com.merin.moviebooking.exception.TicketNotFoundException;
import com.merin.moviebooking.service.IBookingService;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/booking")
@Tag(name = "Book Controller",description = "Booking Management Portal")
public class BookingController 
{
	
	Logger logger=LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private IBookingService bookingService;
	
	
	/** Customer performs the Booking **/
	@PostMapping("/addBooking/{customerId}/{showId}/{ticketId}/{paymentId}")
	public HttpStatus addBooking(@RequestBody Booking booking, @PathVariable("customerId") Integer customerId,@PathVariable("showId")Integer showId, 
	         @PathVariable("ticketId")Integer ticketId,@PathVariable("paymentId")Integer paymentId ) 
			 throws BookingNotFoundException, CustomerNotFoundException, 
			 ShowNotFoundException,TicketNotFoundException,PaymentNotFoundException
	{
		
		
		logger.info("/---------- Booking Done Successfully ----------/");
		boolean bookResult=bookingService.addBooking(booking,customerId,showId,ticketId,paymentId);
		
		if(bookResult==true)
			return HttpStatus.CREATED;
			else
			return HttpStatus.NOT_ACCEPTABLE;
		
	}
	
	
	/** Customer views the Booking By Id **/
	@GetMapping("/viewBookingById/{id}")
	public ResponseEntity<Booking> viewBookingDetailsById(@PathVariable("id") int id) throws BookingNotFoundException 
	{
		
		logger.info("/---------- Fetched The Booking Successfully ----------/");
		Booking booking=bookingService.getBookingDetailsById(id);
		return new ResponseEntity<Booking>(booking,HttpStatus.OK);
	}
	
	
	/** Customer views the Booking By Date **/
	@GetMapping("/viewBookingByDate/{date}") 
	public List<Booking> viewBookingDetailsByDate(@PathVariable("date") 
	  @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date) throws BookingNotFoundException 
	{
		
		logger.info("/---------- Fetched The Booking Details By Date Successfully ----------/");
		return bookingService.getBookingDetailsByDate(date);
		
	}
	
	/** Customer views all the Booking **/
	@GetMapping("/viewAllBookings")
	public List<Booking> viewAllBookings() throws BookingNotFoundException 
	{
		
		logger.info("/---------- Fetched All The Booking Details Successfully ----------/");
		return bookingService.getAllBookingDetails();
	}
	
	
	/** Customer deletes the Booking **/
	@DeleteMapping("/deleteBooingById/{id}")
	public ResponseEntity<Boolean> removeBookingById(@PathVariable("id") int id) throws BookingNotFoundException 
	{
		
		logger.info("/---------- Deleted The Booking Successfully ----------/");
		bookingService.deleteBookingById(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		
	}
	

}
