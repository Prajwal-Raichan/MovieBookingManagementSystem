package com.merin.moviebooking.service;

import java.util.List;

import com.merin.moviebooking.entity.Seat;
import com.merin.moviebooking.exception.SeatNotFoundException;
import com.merin.moviebooking.exception.TicketNotFoundException;

public interface ISeatService 
{

	public void addSeat(Seat seat) throws SeatNotFoundException;

	public void updateSeat(Seat seat,Integer id) throws SeatNotFoundException;

	public void bookSeat(Seat seat,Integer ticketId, Integer seatId) throws SeatNotFoundException, TicketNotFoundException;

	public void cancelSeatBooking(Seat seat, Integer ticketId,Integer seatId) throws SeatNotFoundException, TicketNotFoundException;

	public void blockSeat(Seat seat,Integer seatId) throws SeatNotFoundException;

	public List<Seat> getAllSeatDetails() throws SeatNotFoundException;

}
