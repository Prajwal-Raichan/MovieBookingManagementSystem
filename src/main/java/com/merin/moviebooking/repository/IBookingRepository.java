package com.merin.moviebooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merin.moviebooking.entity.Booking;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Integer>
{

	
	@Query("SELECT b FROM Booking b WHERE b.bookingDate LIKE :bDate")
	public List<Booking> getBookingDetailsByDate(@Param("bDate") @JsonFormat(pattern="yyyy-MM-dd") LocalDate date);

	public boolean existsByBookingDate(LocalDate date);

}
