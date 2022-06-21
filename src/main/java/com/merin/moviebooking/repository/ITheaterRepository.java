package com.merin.moviebooking.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.merin.moviebooking.entity.Theater;


@Repository
public interface ITheaterRepository extends JpaRepository<Theater, Integer>
{

	@Query("SELECT s FROM Theater s WHERE s.theaterName=:name")
	public List<Theater> getShowsByTheaterName(@Param("name")String name);

	
	public boolean existsByTheaterName(String theaterName);


	public List<Theater> getMovieByTheaterCity(String city);


	
}
