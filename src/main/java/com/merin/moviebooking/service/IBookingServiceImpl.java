package com.merin.moviebooking.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merin.moviebooking.entity.Booking;
import com.merin.moviebooking.entity.Confirmation;
import com.merin.moviebooking.entity.Customer;
import com.merin.moviebooking.entity.Payment;
import com.merin.moviebooking.entity.Show;
import com.merin.moviebooking.entity.Ticket;
import com.merin.moviebooking.exception.BookingNotFoundException;
import com.merin.moviebooking.exception.CustomerNotFoundException;
import com.merin.moviebooking.exception.PaymentNotFoundException;
import com.merin.moviebooking.exception.ShowNotFoundException;
import com.merin.moviebooking.exception.TicketNotFoundException;
import com.merin.moviebooking.repository.IBookingRepository;
import com.merin.moviebooking.repository.ICustomerRepository;
import com.merin.moviebooking.repository.IPaymentRepository;
import com.merin.moviebooking.repository.IShowRepository;
import com.merin.moviebooking.repository.ITicketRepository;

@Service
public class IBookingServiceImpl implements IBookingService
{

	@Autowired
	private IBookingRepository bookingRepository;

	@Autowired
	private IShowRepository iShowRepository;

	@Autowired
	private ICustomerRepository iCustomerRepository;

	@Autowired
	private IPaymentRepository iPaymentRepository;

	@Autowired
	private ITicketRepository iTicketRepository;

	
	
	@Override
	public boolean addBooking(Booking booking,Integer customerId,Integer showId,
			Integer ticketId,Integer paymentId) throws BookingNotFoundException,
	CustomerNotFoundException,ShowNotFoundException,TicketNotFoundException,PaymentNotFoundException
	{
		Customer customer=new Customer();
		Show show=new Show();
		Ticket ticket=new Ticket();
		Payment payment=new Payment();

		boolean customerIdBean=iCustomerRepository.existsById(customerId);
		boolean showIdBean=iShowRepository.existsById(showId);
		boolean ticketIdBean=iTicketRepository.existsById(ticketId);
		boolean paymentIdBean=iPaymentRepository.existsById(paymentId);

		if(!customerIdBean)
			throw new CustomerNotFoundException("No Customer Exists with ID: "+customerId+", Enter a Valid Customer ID.!!");
		if(!showIdBean)
			throw new ShowNotFoundException("No Show Exists With ID: "+showId+", Enter a Valid Show ID.!!");
		if(!ticketIdBean)
			throw new TicketNotFoundException("No Ticket Exists With ID: "+ticketId+", Enter a Valid Ticket ID.!!");
		if(!paymentIdBean)
			throw new PaymentNotFoundException("No Payment Exists With ID: "+paymentId+", Enter a Valid Payment ID.!!");
		
			customer=iCustomerRepository.findById(customerId).get();
			show=iShowRepository.findById(showId).get();
			ticket=iTicketRepository.findById(ticketId).get();
			payment=iPaymentRepository.findById(paymentId).get();
			
			Confirmation ticketStatus=ticket.getTicketStatus();
			Confirmation paymentStatus=payment.getPaymentStatus();
			
			if(!ticketStatus.equals(Confirmation.SUCCESSFULL)||!paymentStatus.equals(Confirmation.SUCCESSFULL))
			{
				booking.setBookingStatus("FAILED");
				ticket.setTicketStatus(Confirmation.CANCELLED);
				booking.setTicket(ticket);
				booking.setCustomer(customer);
				booking.setShow(show);
				booking.setPayment(payment);
				booking.setPrice(payment.getPaymentAmount());
				bookingRepository.saveAndFlush(booking);
				
			}
			else
			{
				booking.setCustomer(customer);
				booking.setShow(show);
				ticket.setTicketStatus(Confirmation.SUCCESSFULL);
				booking.setTicket(ticket);
				booking.setPayment(payment);
				booking.setPrice(payment.getPaymentAmount());
				booking.setBookingStatus("SUCCESSFULL");
				bookingRepository.saveAndFlush(booking);
				return true;
				
			}
		 return false;
		
	}


	@Override
	public Booking getBookingDetailsById(int bookingId) throws BookingNotFoundException 
	{
		if(!bookingRepository.existsById(bookingId))
			throw new BookingNotFoundException("No Booking Found with the ID: "+bookingId);

		return bookingRepository.findById(bookingId).get();
	}


	@Override
	public List<Booking> getBookingDetailsByDate(LocalDate date) throws BookingNotFoundException 
	{
		
		if(!bookingRepository.existsByBookingDate(date))
			throw new BookingNotFoundException("There is No Booking with the Date: "+date);
		
		return bookingRepository.getBookingDetailsByDate(date);

	}

	@Override
	public List<Booking> getAllBookingDetails() throws BookingNotFoundException 
	{
		List<Booking> bookingBean=bookingRepository.findAll();
		if(bookingBean.size()==0)
			throw new BookingNotFoundException("There is No Bookings Available.!!");
		return bookingRepository.findAll();
	}


	@Override
	public void deleteBookingById(int id) throws BookingNotFoundException 
	{
		if(!bookingRepository.existsById(id))
			throw new BookingNotFoundException("No Booking Found with the ID: "+id);
		
		bookingRepository.deleteById(id);

	}


}
