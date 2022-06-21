package com.merin.moviebooking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Payment_Details")
public class Payment 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int	paymentId;
	
	
	@Column(name = "Payment_Type")
	private String paymentType;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Payment_Status")
	private	Confirmation paymentStatus;
	
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "Payment_Date")
	private LocalDate paymentDate;
	
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "Payment_Time")
	private	LocalDateTime paymentTime;
	
	
	@Column(name = "Payment_Amount")
	private double paymentAmount;
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Card card;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Upi upi;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private NetBanking netBanking;
	
	
	public Payment()
	{
		super();
	}
	
	public Payment(int paymentId, String paymentType, Confirmation paymentStatus, LocalDate paymentDate,
			LocalDateTime paymentTime, double paymentAmount, Card card, Upi upi, NetBanking netBanking) {
		super();
		this.paymentId = paymentId;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
		this.paymentTime = paymentTime;
		this.paymentAmount = paymentAmount;
		this.card = card;
		this.upi = upi;
		this.netBanking = netBanking;
	}
	

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Confirmation getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Confirmation paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public LocalDateTime getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(LocalDateTime paymentTime) {
		this.paymentTime = paymentTime;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Upi getUpi() {
		return upi;
	}

	public void setUpi(Upi upi) {
		this.upi = upi;
	}

	public NetBanking getNetBanking() {
		return netBanking;
	}

	public void setNetBanking(NetBanking netBanking) {
		this.netBanking = netBanking;
	}

	//[--------- Extra Functionalities ----------]
	@JsonIgnore
	public String getCardNumber() 
	{
		if(card==null)
		card=new Card();
		return card.getCardNumber();
		
	}

	@JsonIgnore
	public int getCvv() 
	{
		if(card==null)
		card=new Card();
		return card.getCvv();
		
	}

	@JsonIgnore
	public String getUpiId() 
	{
		if(upi==null)
		upi=new Upi();
		return upi.getUpiId();
		
	}
	
	@JsonIgnore
	public int getUpiPin() 
	{
		if(upi==null)
		upi=new Upi();
		return upi.getUpiPin();
		
	}

	@JsonIgnore
	public String getNetLoginId() 
	{
		if(netBanking==null)
		netBanking=new NetBanking();
		return netBanking.getNetLoginId();
	}

	@JsonIgnore
	public String getNetPassword() 
	{
		if(netBanking==null)
		netBanking=new NetBanking();
		return netBanking.getNetPassword();
	}

	
}
