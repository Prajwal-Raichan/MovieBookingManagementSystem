package com.merin.moviebooking.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.merin.moviebooking.entity.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Integer>
{

}
