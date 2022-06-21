package com.merin.moviebooking.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Seat_Details")
public class Seat 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Seat_ID")
	private int seatId;
	
	@Column(name = "Seat_Number")
	private String seatNumber;
	
	@Column(name = "Seat_Type")
	private String seatType;
	
	@Column(name = "Seat_Price")
	private double price;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Seat_Status")
	private SeatStatus status;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Ticket ticket;

	public Seat() 
	{
		
	}
	
	
	public Seat(int seatId, String seatNumber, String seatType, double price, SeatStatus status, Ticket ticket) 
	{
		super();
		this.seatId = seatId;
		this.seatNumber = seatNumber;
		this.seatType = seatType;
		this.price = price;
		this.status = status;
		this.ticket = ticket;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public SeatStatus getStatus() {
		return status;
	}

	public void setStatus(SeatStatus status) {
		this.status = status;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	

}
