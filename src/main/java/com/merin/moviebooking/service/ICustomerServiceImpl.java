package com.merin.moviebooking.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merin.moviebooking.entity.Customer;
import com.merin.moviebooking.exception.CustomerNotFoundException;
import com.merin.moviebooking.repository.ICustomerRepository;

@Service
public class ICustomerServiceImpl implements ICustomerService
{

	@Autowired
	private ICustomerRepository customerRepository;
	


	@Override
	public void updateCustomer(Customer customer) throws CustomerNotFoundException 
	{
		if(!customerRepository.existsById(customer.getCustomerId()))
			throw new CustomerNotFoundException("No Customer Exists With Id:"+customer.getCustomerId());
			customerRepository.findById(customer.getCustomerId()).get();
			customerRepository.saveAndFlush(customer);
	}



	@Override
	public Customer getCustomerDetails(int id) throws CustomerNotFoundException
	{ 
		if(!customerRepository.existsById(id))
			throw new CustomerNotFoundException("No Customer Exists With Id:"+id);
		return customerRepository.findById(id).get();
	}


	@Override
	public List<Customer> getAllCustomers() throws CustomerNotFoundException
	{
		List<Customer> customerInventory=customerRepository.findAll();
		if(customerInventory.size()==0)
			throw new CustomerNotFoundException("No Customer Exists With in Database");
		
		return customerInventory;
	}


	@Override
	public void deleteCustomerById(int id) throws CustomerNotFoundException
	{
		if(!customerRepository.existsById(id))
			throw new CustomerNotFoundException("No Customer Exists With Id:"+id);
		customerRepository.deleteById(id);

	}


}
