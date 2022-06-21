package com.merin.moviebooking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Ticket_Details")
public class Ticket 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Ticket_Id")
	private int ticketId;


	@Column(name="Total_SeatCount")
	private int noOfSeats;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="Ticket_Status")
	private Confirmation ticketStatus;


	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "Show_Date")
	private LocalDate showDate;
	
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "Show_Time")
	private LocalDateTime showTime;
	
	
	@Column(name = "Ticket_Price")
	private double ticketPrice;
	
	@JsonIgnore
	@OneToMany
	private List<Seat> seat;
	
	
	public Ticket() 
	{
		super();
	}


	public Ticket(int ticketId, int noOfSeats, Confirmation ticketStatus, LocalDate showDate, LocalDateTime showTime,
			double ticketPrice, List<Seat> seat) {
		super();
		this.ticketId = ticketId;
		this.noOfSeats = noOfSeats;
		this.ticketStatus = ticketStatus;
		this.showDate = showDate;
		this.showTime = showTime;
		this.ticketPrice = ticketPrice;
		this.seat = seat;
	}


	public int getTicketId() {
		return ticketId;
	}


	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}


	public int getNoOfSeats() {
		return noOfSeats;
	}


	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}


	public Confirmation getTicketStatus() {
		return ticketStatus;
	}


	public void setTicketStatus(Confirmation ticketStatus) {
		this.ticketStatus = ticketStatus;
	}


	public LocalDate getShowDate() {
		return showDate;
	}


	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}


	public LocalDateTime getShowTime() {
		return showTime;
	}


	public void setShowTime(LocalDateTime showTime) {
		this.showTime = showTime;
	}


	public double getTicketPrice() {
		return ticketPrice;
	}


	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}


	public List<Seat> getSeat() {
		return seat;
	}


	public void setSeat(List<Seat> seat) {
		this.seat = seat;
	}



}
