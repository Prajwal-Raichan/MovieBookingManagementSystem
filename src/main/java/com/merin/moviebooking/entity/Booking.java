package com.merin.moviebooking.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Booking_Details")
public class Booking 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Booking_Id")
	private int bookingId;
	
	@Column(name = "Booking_Status")
	private String bookingStatus;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "Booking_Date")
	private LocalDate bookingDate;
	
	@Column(name = "Booking_Price")
	private double price;
	
	@JsonIgnore
	@ManyToOne
	private Customer customer;
	
	@JsonIgnore
	@OneToOne
	private Ticket ticket;
	
	@JsonIgnore
	@OneToOne
	private Show show;
	
	@JsonIgnore
    @OneToOne
    private Payment payment;
	
	
	public Booking() 
	{
		super();
	}


	public Booking(int bookingId, String bookingStatus, LocalDate bookingDate, double price, Customer customer,
			Ticket ticket, Show show, Payment payment) {
		super();
		this.bookingId = bookingId;
		this.bookingStatus = bookingStatus;
		this.bookingDate = bookingDate;
		this.price = price;
		this.customer = customer;
		this.ticket = ticket;
		this.show = show;
		this.payment = payment;
	}


	public int getBookingId() {
		return bookingId;
	}


	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}


	public String getBookingStatus() {
		return bookingStatus;
	}


	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}


	public LocalDate getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Ticket getTicket() {
		return ticket;
	}


	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


	public Show getShow() {
		return show;
	}


	public void setShow(Show show) {
		this.show = show;
	}


	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	
}
