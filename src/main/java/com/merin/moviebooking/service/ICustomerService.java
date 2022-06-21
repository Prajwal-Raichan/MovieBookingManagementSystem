package com.merin.moviebooking.service;

import java.util.List;
import com.merin.moviebooking.entity.Customer;
import com.merin.moviebooking.exception.CustomerNotFoundException;

public interface ICustomerService 
{

	public void updateCustomer(Customer customer) throws CustomerNotFoundException;

	public Customer getCustomerDetails(int id) throws CustomerNotFoundException;

	public List<Customer> getAllCustomers() throws CustomerNotFoundException;

	public void deleteCustomerById(int id) throws CustomerNotFoundException;
	

}
