package com.merin.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.merin.moviebooking.entity.NetBanking;

@Repository
public interface INetBankingRepository extends JpaRepository<NetBanking, Integer>
{
	

}
