package com.merin.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.merin.moviebooking.entity.PaymentMasterData;

@Repository
public interface IPaymentMasterDataRepository extends JpaRepository<PaymentMasterData, Integer>
{

	@Query("SELECT m FROM PaymentMasterData m WHERE m.cardNumber LIKE :cNumber")
	public PaymentMasterData validateCredentialsForCard(@Param("cNumber")String cNumber);
	
    public boolean existsByUpiId(String rUpi);
    
    public boolean existsByUpiPin(int pin);

	public boolean existsByNetLoginId(String rLoginId);

	public boolean existsByNetPassword(String rNetPassword);
    
}
