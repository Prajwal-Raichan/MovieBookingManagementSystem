package com.merin.moviebooking.entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="User_Details")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "User_Id")
	private int userId;
	
	@Column(name = "Username",unique = true)
	private String userName;
	
	@Column(name="Password")
	private String password;
	
	@Column(name = "UserRole")
	private String role;  
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;     
	
	public User() 
	{
		super();
	}


	public User(String userName, String password, String role, Customer customer) 
	{
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.customer = customer;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	//[Extra functionalities]
	@JsonIgnore
	public String getContactNumber()
	{

		return customer.getMobileNumber();
	}

	@JsonIgnore
	public String getEmailId()
	{

		return customer.getEmailId();
	}
	
	@JsonIgnore
	public String getCustomerName()
	{

		return customer.getCustomerName();
	}
	
}
