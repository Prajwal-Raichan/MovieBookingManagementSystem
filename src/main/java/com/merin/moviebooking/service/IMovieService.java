package com.merin.moviebooking.service;

import java.util.List;

import com.merin.moviebooking.entity.Movie;
import com.merin.moviebooking.entity.Theater;
import com.merin.moviebooking.exception.MovieNotFoundException;
import com.merin.moviebooking.exception.ShowNotFoundException;

public interface IMovieService 
{

	public void addMovieToShow(Movie movie,Integer showId) throws MovieNotFoundException, ShowNotFoundException;

	public void updateMovie(Movie movie) throws MovieNotFoundException;

	public Movie getMovieDetails(int id) throws MovieNotFoundException;

	public List<Theater> getMovieByCity(String city);
	
	public List<Movie> getAllTheMovieDetails() throws MovieNotFoundException;

	public void removeMovie(int id);	
	
	
}
