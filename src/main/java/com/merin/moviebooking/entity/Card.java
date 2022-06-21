package com.merin.moviebooking.entity;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Card_Details")
public class Card 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Card_Id")
	private int cardId;
	
	@Column(name = "Card_HolderName")
	private String cardHolderName;
	
	@Column(name = "Card_Number")
	private String cardNumber;
	
	@Column(name = "Card_CVV")
	private int cvv;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "Card_Expiry")
	private	LocalDate cardExpiry;
	

	

	public Card() 
	{
		super();
	}

	
	public Card(String cardHolderName, String cardNumber, int cvv, LocalDate cardExpiry) 
	{
		super();
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.cardExpiry = cardExpiry;
	}


	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
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

	
}
