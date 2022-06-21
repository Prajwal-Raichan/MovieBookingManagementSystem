package com.merin.moviebooking.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.merin.moviebooking.entity.Customer;
import com.merin.moviebooking.exception.CustomerNotFoundException;
import com.merin.moviebooking.service.ICustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer Controller",description = "Customer Management Portal")
public class CustomerController 
{
	Logger logger=LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private ICustomerService customerService;


	
	/** Customer updates his/her Profile **/
	@PutMapping("/updateCustomer") 
	public HttpStatus updateUser(@RequestBody Customer customer) throws CustomerNotFoundException 
	{
		
		customerService.updateCustomer(customer);
		logger.info("/---------- Customer Updated Successfully ----------/");
		return HttpStatus.ACCEPTED;
			
	}

	
	/** Admin views the Customer By Id **/
	@GetMapping("/viewCustomerById/{id}")
	public ResponseEntity<Customer> viewCustomerById(@PathVariable("id") int id) throws CustomerNotFoundException
	{
		
		
		ResponseEntity<Customer> customerBean=null;
		Customer customer=null;
		try {
			customer= customerService.getCustomerDetails(id);
			customerBean=new ResponseEntity<Customer>(customer,HttpStatus.OK);
		} catch (Exception e) 
		{
			customerBean=new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return customerBean;
		}
		logger.info("/---------- Customer Fetched Successfully----------/");
		return customerBean;

	}

	
	/** Admin views all the Customers **/
	@GetMapping("/viewAllCustomers")
	public List<Customer> getAllCustomers() throws CustomerNotFoundException
	{
		
		logger.info("/---------- Fetched All The Customers Successfully ----------/");
		return customerService.getAllCustomers();


	}
	
	
	/** Customer deletes his Profile By Id **/
	@DeleteMapping("/deleteCustomerById/{id}")
	public ResponseEntity<Boolean> removeCustomer(@PathVariable("id") int id) throws CustomerNotFoundException
	{
		logger.info("/---------- Customer Fetched Successfully ----------/");
		customerService.deleteCustomerById(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		

	}


}
