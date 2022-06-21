package com.merin.moviebooking.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merin.moviebooking.entity.Confirmation;
import com.merin.moviebooking.entity.Seat;
import com.merin.moviebooking.entity.SeatStatus;
import com.merin.moviebooking.entity.Ticket;
import com.merin.moviebooking.exception.SeatNotFoundException;
import com.merin.moviebooking.exception.TicketNotFoundException;
import com.merin.moviebooking.repository.ISeatRepository;
import com.merin.moviebooking.repository.ITicketRepository;

@Service
public class ISeatServiceImpl implements ISeatService
{
	@Autowired
	private ISeatRepository seatRepository;


	@Autowired
	private ITicketRepository iTicketRepository;

	@Override
	public void addSeat(Seat seat) throws SeatNotFoundException
	{
		if(seatRepository.existsById(seat.getSeatId()))
			throw new SeatNotFoundException("Seat Already Exists In the Screen.!!");

		seatRepository.saveAndFlush(seat);

	}

	@Override
	public void updateSeat(Seat seat, Integer id) throws SeatNotFoundException
	{
		if(!seatRepository.existsById(id))
			throw new SeatNotFoundException("Seat Doesn't Exists with ID: "+id);
		
		seat.setSeatId(id);
		seatRepository.findById(seat.getSeatId()).get();
		seatRepository.saveAndFlush(seat);

	}

	@Override
	public void bookSeat(Seat seat,Integer ticketId,Integer seatId) throws SeatNotFoundException, TicketNotFoundException
	{
		if(!seatRepository.existsById(seatId))
			throw new SeatNotFoundException("Seat Doesn't Exists In the Screen.!!");
		if(!iTicketRepository.existsById(ticketId))
			throw new TicketNotFoundException("Ticket Doesn't Exists with ID: "+ticketId);

		// [Verification for SeatStatus]
		Seat seatStatus=seatRepository.findById(seatId).get();
		SeatStatus seatBean1=seatStatus.getStatus();
		SeatStatus seatBean2=SeatStatus.BOOKED;
		SeatStatus seatBean3=SeatStatus.CANCELLED;
		SeatStatus seatBean4=SeatStatus.BLOCKED;

		if(seatBean1.equals(seatBean2))
			throw new SeatNotFoundException("Sorry.!! Seat is Already BOOKED.!!");
		if(seatBean1.equals(seatBean3))
			throw new SeatNotFoundException("Sorry.!! Seat has been Already CANCELLED.!!");
		if(seatBean1.equals(seatBean4))
			throw new SeatNotFoundException("Sorry.!! Seat has been BLOCKED.!!");

		Ticket ticket=new Ticket();
		ticket=iTicketRepository.findById(ticketId).get();
		Seat seatUpdate=seatRepository.findById(seatId).get();
		
		//[Since We cannot Set List directly, We are using ArrayList Implementation Object]
		List<Seat> seatBean=new ArrayList<>();
		List<Seat> seatRepo=seatRepository.findAll();
		for(Seat s:seatRepo)
		{
			if(s.getTicket()==ticket)
				seatBean.add(s); 
		}

		//[Using another duplicate Object to Set everything in Current Seat Object]
		seat.setSeatId(seatId);
		seat.setSeatNumber(seatUpdate.getSeatNumber());
		seat.setSeatType(seatUpdate.getSeatType());
		seat.setPrice(seatUpdate.getPrice());
		seat.setTicket(ticket);
		seat.setStatus(SeatStatus.BOOKED);
		seatBean.add(seat);
		ticket.setSeat(seatBean);
		
		
		/** [Ticket Price Logic]  **/
		double bill=seat.getPrice();
		List<Seat> totalTicketPrice=seatRepository.findAll();
		for(Seat sPrice:totalTicketPrice)
		{
			if(sPrice.getTicket()==ticket)
				bill+=sPrice.getPrice();
		}
		ticket.setTicketPrice(bill);
		ticket.setTicketStatus(Confirmation.SUCCESSFULL);

		seatRepository.saveAndFlush(seat);
		

	}


	@Override
	public void cancelSeatBooking(Seat seat,Integer ticketId,Integer seatId) throws SeatNotFoundException, TicketNotFoundException
	{

		if(!seatRepository.existsById(seatId))
			throw new SeatNotFoundException("Seat Doesn't Exists In the Screen.!!");
		if(!iTicketRepository.existsById(ticketId))
			throw new TicketNotFoundException("Ticket Doesn't Exists with ID: "+ticketId);


		Seat seatStatus=seatRepository.findById(seatId).get();
		SeatStatus seatBean1=seatStatus.getStatus();
		SeatStatus seatBean2=SeatStatus.CANCELLED;

		if(seatBean1.equals(seatBean2))
			throw new SeatNotFoundException("Seat has Already been CANCELLED.!!");

		Ticket ticket=new Ticket();
		ticket=iTicketRepository.findById(ticketId).get();
		Seat seatUpdate=seatRepository.findById(seatId).get();

		//[Since We cannot Set List directly, We are using ArrayList Implementation Object]
		List<Seat> seatBean=new ArrayList<>();
		List<Seat> seatRepo=seatRepository.findAll();
		for(Seat s:seatRepo)
		{
			if(s.getTicket()==ticket)
				seatBean.add(s); 
		}
		
		//[Using another duplicate Object to Set everything in Current Seat Object]
		
		seat.setSeatId(seatId);
		seat.setSeatNumber(seatUpdate.getSeatNumber());
		seat.setSeatType(seatUpdate.getSeatType());
		seat.setPrice(seatUpdate.getPrice());
		seat.setTicket(ticket);
		seat.setStatus(SeatStatus.CANCELLED);
		seatBean.add(seat);
		
		ticket.setSeat(seatBean);
		ticket.setTicketStatus(Confirmation.CANCELLED);

		seatRepository.saveAndFlush(seat);
		

	}

	
	@Override
	public void blockSeat(Seat seat, Integer seatId) throws SeatNotFoundException
	{
		
		if(!seatRepository.existsById(seatId))
			throw new SeatNotFoundException("Seat Doesn't Exists In the Screen.!!");
		
		Seat seatStatus=seatRepository.findById(seatId).get();
		SeatStatus seatBean1=seatStatus.getStatus();
		SeatStatus seatBean2=SeatStatus.BLOCKED;

		if(seatBean1.equals(seatBean2))
			throw new SeatNotFoundException("Seat has Already been BLOCKED.!!");

		seat.setSeatId(seatId);
		seat.setStatus(SeatStatus.BLOCKED);
		seatRepository.saveAndFlush(seat);

	}

	@Override
	public List<Seat> getAllSeatDetails() throws SeatNotFoundException
	{
		List<Seat> seatBean=seatRepository.findAll();
		if(seatBean.size()==0)
			throw new SeatNotFoundException("No Seat is Added in the Theater.!!");

		return seatRepository.findAll();
	}



}
