package com.merin.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.merin.moviebooking.entity.Movie;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer>
{
	

}
