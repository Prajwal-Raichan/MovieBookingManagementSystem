package com.merin.moviebooking.service;


import java.time.LocalDate;
import com.merin.moviebooking.entity.Payment;
import com.merin.moviebooking.exception.PaymentNotFoundException;
import com.merin.moviebooking.exception.TicketNotFoundException;

public interface IPaymentService 
{

	
	public void addPaymentByCard(Payment payment,Integer ticketId) throws PaymentNotFoundException, TicketNotFoundException;

	public void addPaymentByUpi(Payment payment,Integer ticketId) throws PaymentNotFoundException,TicketNotFoundException;
	
	public void addPaymentByNetBanking(Payment payment,Integer ticketId) throws PaymentNotFoundException,TicketNotFoundException;
	
	public double getPaymentRevenueByDate(LocalDate date);

	public double getGrossPaymentRevenue();

	public Payment getPaymentDetailsById(int id);
	
	public void deletePaymentById(int id) throws PaymentNotFoundException;


}
