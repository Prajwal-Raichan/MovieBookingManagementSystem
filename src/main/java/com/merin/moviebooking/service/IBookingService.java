package com.merin.moviebooking.service;

import java.time.LocalDate;
import java.util.List;
import com.merin.moviebooking.entity.Booking;
import com.merin.moviebooking.exception.BookingNotFoundException;
import com.merin.moviebooking.exception.CustomerNotFoundException;
import com.merin.moviebooking.exception.PaymentNotFoundException;
import com.merin.moviebooking.exception.ShowNotFoundException;
import com.merin.moviebooking.exception.TicketNotFoundException;

public interface IBookingService 
{

	public boolean addBooking(Booking booking,Integer customerId,Integer showId,
		Integer ticketId,Integer paymentId) throws BookingNotFoundException,CustomerNotFoundException,
	    ShowNotFoundException,TicketNotFoundException,PaymentNotFoundException;

	public Booking getBookingDetailsById(int bookingId) throws BookingNotFoundException ;

	public List<Booking> getAllBookingDetails() throws BookingNotFoundException ;

	public void deleteBookingById(int id) throws BookingNotFoundException ;

	public List<Booking> getBookingDetailsByDate(LocalDate date) throws BookingNotFoundException ;

}
