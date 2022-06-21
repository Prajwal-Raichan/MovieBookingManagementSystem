package com.merin.moviebooking.repository;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import com.merin.moviebooking.entity.Show;

public interface IShowRepository extends JpaRepository<Show, Integer>
{

	public boolean existsByShowDate(LocalDate Date);

	
}
