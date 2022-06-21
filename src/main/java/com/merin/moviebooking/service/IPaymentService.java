package com.merin.moviebooking.service;


import java.time.LocalDate;

import com.merin.moviebooking.entity.Payment;
import com.merin.moviebooking.entity.PaymentMasterData;
import com.merin.moviebooking.exception.PaymentNotFoundException;

public interface IPaymentService 
{

	public void addMasterData(PaymentMasterData masterData) throws PaymentNotFoundException;
	
	public void addPaymentByCard(Payment payment) throws PaymentNotFoundException;

	public void addPaymentByUpi(Payment payment) throws PaymentNotFoundException;
	
	public void addPaymentByNetBanking(Payment payment) throws PaymentNotFoundException;
	
	public double getPaymentRevenueByDate(LocalDate date);

	public double getGrossPaymentRevenue();

	public Payment getPaymentDetailsById(int id);
	
	public void deletePaymentById(int id) throws PaymentNotFoundException;


}
