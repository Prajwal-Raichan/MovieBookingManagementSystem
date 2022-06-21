package com.merin.moviebooking.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Customer_Details")
public class Customer 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Customer_Id")
	private int customerId;
	
	@Column(name="Customer_Name")
	private String customerName;
	
	@Column(name="DateOfBirth")
	private LocalDate dob;
	
	@Column(name = "Email_ID",unique = true)
	private String emailId;
	
	@Column(name = "Mobile_Number",unique = true)
	private String mobileNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	
	@JsonIgnore
	@OneToMany
	private List<Booking> bookings;
	
	
	public Customer() 
	{
		super();
	}


	public Customer(int customerId, String customerName, LocalDate dob, String emailId, String mobileNumber,
			Address address, List<Booking> bookings) 
	{
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.dob = dob;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.bookings = bookings;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public List<Booking> getBookings() {
		return bookings;
	}


	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}



}
