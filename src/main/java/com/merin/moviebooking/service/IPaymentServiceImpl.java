package com.merin.moviebooking.service;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merin.moviebooking.controller.PaymentController;
import com.merin.moviebooking.entity.Confirmation;
import com.merin.moviebooking.entity.Payment;
import com.merin.moviebooking.entity.Ticket;
import com.merin.moviebooking.exception.PaymentNotFoundException;
import com.merin.moviebooking.exception.TicketNotFoundException;
import com.merin.moviebooking.repository.IPaymentRepository;
import com.merin.moviebooking.repository.ITicketRepository;


@Service
public class IPaymentServiceImpl implements IPaymentService
{

	@Autowired
	private IPaymentRepository paymentRepository;
	
	@Autowired
	private ITicketRepository iTicketRepository;


	 Logger logger=LoggerFactory.getLogger(PaymentController.class);
	
	
	

	@Override
	public void addPaymentByCard(Payment payment, Integer ticketId) throws PaymentNotFoundException, TicketNotFoundException
	{
		if(!iTicketRepository.existsById(ticketId))
			throw new TicketNotFoundException("Ticket ID Doesn't Exists");
		
		Ticket ticketBean=iTicketRepository.findById(ticketId).get();
		double amount=payment.getPaymentAmount();
		if(ticketBean.getTicketPrice()!=amount)
		{
			payment.setPaymentStatus(Confirmation.FAILED);
			paymentRepository.saveAndFlush(payment);
			logger.info("/---------- Ticket Amount Didn't Match, Please Try Again ----------/");
			throw new PaymentNotFoundException("Incorrect Ticket Amount ");
		}
		else
		{
			payment.setPaymentStatus(Confirmation.SUCCESSFULL);
			paymentRepository.saveAndFlush(payment);
			logger.info("/---------- Payment Done By CARD Successfully ----------/");
		}
	}


	@Override
	public void addPaymentByUpi(Payment payment,Integer ticketId) throws PaymentNotFoundException,TicketNotFoundException
	{
		if(!iTicketRepository.existsById(ticketId))
			throw new TicketNotFoundException("Ticket ID Doesn't Exists");
		
		Ticket ticketBean=iTicketRepository.findById(ticketId).get();
		double amount=payment.getPaymentAmount();
		if(ticketBean.getTicketPrice()!=amount)
		{
			payment.setPaymentStatus(Confirmation.FAILED);
			paymentRepository.saveAndFlush(payment);
			logger.info("/---------- Ticket Amount Didn't Match, Please Try Again ----------/");
			throw new PaymentNotFoundException("Incorrect Ticket Amount ");
		}
		else
		{
			payment.setPaymentStatus(Confirmation.SUCCESSFULL);
			logger.info("/---------- Payment Done By UPI Successfully ----------/");
			paymentRepository.saveAndFlush(payment);
		}
	}



	@Override
	public void addPaymentByNetBanking(Payment payment,Integer ticketId) throws PaymentNotFoundException,TicketNotFoundException 
	{
		if(!iTicketRepository.existsById(ticketId))
			throw new TicketNotFoundException("Ticket ID Doesn't Exists");
		
		Ticket ticketBean=iTicketRepository.findById(ticketId).get();
		double amount=payment.getPaymentAmount();
		if(ticketBean.getTicketPrice()!=amount)
		{
			payment.setPaymentStatus(Confirmation.FAILED);
			paymentRepository.saveAndFlush(payment);
			logger.info("/---------- Ticket Amount Didn't Match, Please Try Again ----------/");
			throw new PaymentNotFoundException("Incorrect Ticket Amount ");
		}
		else
		{
			payment.setPaymentStatus(Confirmation.SUCCESSFULL);
			logger.info("/---------- Payment Done By Net-Banking Successfully ----------/");
			paymentRepository.saveAndFlush(payment);
		}	
	}


	@Override
	public Payment getPaymentDetailsById(int id) 
	{

		logger.info("/---------- Fetched Payment Details Successfully ----------/");
		return paymentRepository.findById(id).get();
		
	}


	@Override
	public double getPaymentRevenueByDate(LocalDate date) 
	{
		List<Payment> payment=paymentRepository.getPaymentRevenueByDate(date);
		double revenue = 0.0;
		for(Payment paymentBean:payment)
		{
			if(paymentBean.getPaymentStatus()==Confirmation.SUCCESSFULL)
				revenue+=paymentBean.getPaymentAmount();
		}
		logger.info("/---------- Revenue For Date:"+date+" ----------/");
		return revenue;
		
	}


	@Override
	public double getGrossPaymentRevenue() 
	{
		List<Payment> payment=paymentRepository.findAll();
		double revenue=0.0;

		for(Payment paymentBean:payment)
		{
			if(paymentBean.getPaymentStatus()==Confirmation.SUCCESSFULL)
				revenue+=paymentBean.getPaymentAmount();
		}
		logger.info("/---------- Total Gross Revenue: ----------/");
		return revenue;
		
	}


	@Override
	public void deletePaymentById(int id) throws PaymentNotFoundException
	{
		
		logger.info("/---------- Payment Deleted Successfully ----------/");
		paymentRepository.deleteById(id);

	}

}
