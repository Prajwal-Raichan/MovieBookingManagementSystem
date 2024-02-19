package com.merin.moviebooking.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.merin.moviebooking.entity.Payment;
import com.merin.moviebooking.exception.PaymentNotFoundException;
import com.merin.moviebooking.exception.TicketNotFoundException;
import com.merin.moviebooking.service.IPaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/payment")
@Tag(name = "Payment Controller",description = "Payment Management Portal")
public class PaymentController            
{
	
	@Autowired
	private IPaymentService paymentService;
	
	
	/** 
	 * @param Card, ticketId
	 * @throws PaymentNotFoundException
	 * 
	 * 
	 * Customer performs the payment By Card **/
	@PostMapping("/byCard/{ticketId}")
	public HttpStatus addPaymentByCard(@RequestBody Payment payment, @PathVariable("ticketId") Integer ticketId) throws PaymentNotFoundException,TicketNotFoundException
	{
		
		paymentService.addPaymentByCard(payment,ticketId);
		return HttpStatus.ACCEPTED;
		
	}
	
	/** Customer performs the payment By UPI **/
	@PostMapping("/byUpi/{ticketId}")
	public HttpStatus addPaymentByUpi(@RequestBody Payment payment,@PathVariable("ticketId") Integer ticketId) throws PaymentNotFoundException,TicketNotFoundException
	{
		
		paymentService.addPaymentByUpi(payment,ticketId);
		return HttpStatus.ACCEPTED;
		
	}
	
	
	/** Customer performs the payment By NetBanking**/
	@PostMapping("/byNetBanking/{ticketId}")
	public HttpStatus addPaymentByNetBanking(@RequestBody Payment payment,@PathVariable("ticketId") Integer ticketId) throws PaymentNotFoundException,TicketNotFoundException
	{
		
		paymentService.addPaymentByNetBanking(payment,ticketId);
		return HttpStatus.ACCEPTED;
		
	}
	
	/** Customer views the payment By Id**/
	@GetMapping("viewPaymentById/{id}")
	public Payment getPaymentDetailsById(@PathVariable("id") int id)
	{
		return paymentService.getPaymentDetailsById(id);
		
	}
	
	/** Admin views the Revenue By Date **/
	@GetMapping("/viewRevenueByDate/{date}")
	public double getPaymentRevenueByDate(@PathVariable("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date)
	{
		double revenue=paymentService.getPaymentRevenueByDate(date);
		return revenue;
		
		
	}
	
	/** Admin views the Gross Revenue **/
	@GetMapping("/viewGrossRevenue")
	public double getGrossPaymentRevenue()
	{
		double revenue=paymentService.getGrossPaymentRevenue();
		return revenue;
	}
	
	
	/** Customer deletes the  Payment By Id **/
	@DeleteMapping("/deletePaymentById/{id}")
	public HttpStatus removePaymentById(@PathVariable("id") int id) throws  PaymentNotFoundException
	{
		
		paymentService.deletePaymentById(id);
		return HttpStatus.CREATED;
		
	}
	
	
}
