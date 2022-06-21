package com.merin.moviebooking.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Master_Confidential_Data")
public class PaymentMasterData 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Master_Id")
	private int masterId;
	
	
	/**---------- Customer Details ----------**/ 
	@OneToOne
	private Customer customer;
	
	
	/**---------- Card Details ----------**/ 
	@Column(name = "Card_HolderName")
	private String cardHolderName;
	
	
	@Column(name = "Card_Number",unique = true)
	private String cardNumber;
	
	@Column(name = "Card_Cvv",unique = true)
	private int cvv;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "Card_ExpiryDate")
	private	LocalDate cardExpiry;
	
	
	/**---------- UPI Details ----------**/ 
	@Column(name = "Upi_Id",unique = true)
	private String upiId;
	
	@Column(name = "Upi_PIN")
	private int upiPin;
	
	
	/**---------- Net Banking Details ----------**/ 
	@Column(name = "NetBankingLogin_Id",unique = true)
	private String netLoginId;
	
	
	@Column(name = "NetBanking_Password",unique = true)
	private String netPassword;

	
	public PaymentMasterData() 
	{
		super();
	}
	
	public PaymentMasterData(int masterId, Customer customer, String cardHolderName, String cardNumber, int cvv,
			LocalDate cardExpiry, String upiId, int upiPin, String netLoginId, String netPassword) 
	{
		super();
		this.masterId = masterId;
		this.customer = customer;
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.cardExpiry = cardExpiry;
		this.upiId = upiId;
		this.upiPin = upiPin;
		this.netLoginId = netLoginId;
		this.netPassword = netPassword;
	}


	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public LocalDate getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(LocalDate cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public int getUpiPin() {
		return upiPin;
	}

	public void setUpiPin(int upiPin) {
		this.upiPin = upiPin;
	}

	public String getNetLoginId() {
		return netLoginId;
	}

	public void setNetLoginId(String netLoginId) {
		this.netLoginId = netLoginId;
	}

	public String getNetPassword() {
		return netPassword;
	}

	public void setNetPassword(String netPassword) {
		this.netPassword = netPassword;
	}

	
	//[--------- Extra Functionalities ----------]
	@JsonIgnore
	public int getCustomerId()
	{
		if(customer==null)
		customer=new Customer();
		return customer.getCustomerId();
	}
	
}
