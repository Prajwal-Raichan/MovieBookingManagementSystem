package com.merin.moviebooking.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merin.moviebooking.entity.Payment;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer>
{
	
	@Query("SELECT p FROM Payment p WHERE p.paymentDate LIKE :paymentDate")
	public List<Payment> getPaymentRevenueByDate(@Param("paymentDate") @JsonFormat(pattern="yyyy-MM-dd") LocalDate date);

	
	
}
