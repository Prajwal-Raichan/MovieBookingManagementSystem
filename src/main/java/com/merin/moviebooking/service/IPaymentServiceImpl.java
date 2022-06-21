package com.merin.moviebooking.service;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merin.moviebooking.entity.Confirmation;
import com.merin.moviebooking.entity.Payment;
import com.merin.moviebooking.entity.PaymentMasterData;
import com.merin.moviebooking.exception.PaymentNotFoundException;
import com.merin.moviebooking.repository.IPaymentMasterDataRepository;
import com.merin.moviebooking.repository.IPaymentRepository;


@Service
public class IPaymentServiceImpl implements IPaymentService
{

	@Autowired
	private IPaymentRepository paymentRepository;

	@Autowired
	private IPaymentMasterDataRepository masterDataRepository;

	 Logger logger=LoggerFactory.getLogger(IPaymentServiceImpl.class);
	
	
	
	@Override
	public void addMasterData(PaymentMasterData masterData) throws PaymentNotFoundException
	{
		if(masterDataRepository.existsByUpiId(masterData.getUpiId()))
			new PaymentNotFoundException("Master Data Already Exists, You are not Authorized to add it again.!!");
		if(masterDataRepository.existsByUpiId(masterData.getCardNumber()))
			new PaymentNotFoundException("Master Data Already Exists, You are not Authorized to add it again.!!");
		if(masterDataRepository.existsByUpiId(masterData.getNetLoginId()))
			new PaymentNotFoundException("Master Data Already Exists, You are not Authorized to add it again.!!");

		logger.info("/---------- Master Data Added Successfully ----------/");
		masterDataRepository.saveAndFlush(masterData);

	}

	@Override
	public void addPaymentByCard(Payment payment) throws PaymentNotFoundException
	{

		String rNumber=payment.getCardNumber();
		int rCvv=payment.getCvv();

		PaymentMasterData paymentBean=masterDataRepository.validateCredentialsForCard(rNumber);
		if(paymentBean==null)
		{
			payment.setPaymentStatus(Confirmation.FAILED);
			paymentRepository.saveAndFlush(payment);
			logger.info("/---------- Master Data Cannot be Added ----------/");
			throw new PaymentNotFoundException("Master Data Doesn't Exists.!!");
		}
		
		if(paymentBean.getCvv()!=rCvv)
		{
			payment.setPaymentStatus(Confirmation.FAILED);
			paymentRepository.saveAndFlush(payment);
			logger.info("/---------- Master Data Authentication Failed ----------/");
			throw new PaymentNotFoundException("Master Data Didn't Match, Wrong Credentuals.!!");
		}
		else	
		{
			payment.setPaymentStatus(Confirmation.SUCCESSFULL);
			logger.info("/---------- Payment Done By Card Successfully ----------/");
			paymentRepository.saveAndFlush(payment);
			
		}
	}


	@Override
	public void addPaymentByUpi(Payment payment) throws PaymentNotFoundException 
	{

		String rUpi=payment.getUpiId();
		int rUpiPin=payment.getUpiPin();

		boolean upiIdBean=masterDataRepository.existsByUpiId(rUpi);
		boolean upiPinBean=masterDataRepository.existsByUpiPin(rUpiPin);

		if(upiIdBean!=true||upiPinBean!=true)
		{
			payment.setPaymentStatus(Confirmation.FAILED);
			paymentRepository.saveAndFlush(payment);
			logger.info("/---------- Master Data Authentication Failed ----------/");
			throw new PaymentNotFoundException("Master Data Didn't Match, Wrong Credentials.!!");
		}
		else
		{
			payment.setPaymentStatus(Confirmation.SUCCESSFULL);
			logger.info("/---------- Payment Done By UPI Successfully ----------/");
			paymentRepository.saveAndFlush(payment);
		}

	}


	@Override
	public void addPaymentByNetBanking(Payment payment) throws PaymentNotFoundException 
	{
		String rLoginId=payment.getNetLoginId();
		String rNetPassword=payment.getNetPassword();

		boolean beanLoginId=masterDataRepository.existsByNetLoginId(rLoginId);
		boolean beanNetPassword=masterDataRepository.existsByNetPassword(rNetPassword);

		if(beanLoginId!=true||beanNetPassword!=true)
		{
			payment.setPaymentStatus(Confirmation.FAILED);
			paymentRepository.saveAndFlush(payment);
			logger.info("/---------- Master Data Authentication Failed ----------/");
			throw new PaymentNotFoundException("Master Data Didn't Match, Wrong Credentials.!!");
			
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
