package com.merin.moviebooking.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merin.moviebooking.entity.Movie;
import com.merin.moviebooking.entity.Show;
import com.merin.moviebooking.entity.Theater;
import com.merin.moviebooking.exception.MovieNotFoundException;
import com.merin.moviebooking.exception.ShowNotFoundException;
import com.merin.moviebooking.repository.IMovieRepository;
import com.merin.moviebooking.repository.IShowRepository;
import com.merin.moviebooking.repository.ITheaterRepository;

@Service
public class IMovieServiceImpl implements IMovieService
{
	Logger logger=LoggerFactory.getLogger(IMovieServiceImpl.class);
	
	@Autowired
	private IMovieRepository movieRepository;
	
	@Autowired
	private IShowRepository iShowRepository;
	
	@Autowired
	private ITheaterRepository iTheaterRepository;

	
	@Override
	public void addMovieToShow(Movie movie, Integer showId) throws MovieNotFoundException, ShowNotFoundException
	{
		if(!iShowRepository.existsById(showId))
			throw new ShowNotFoundException("Show Doesn't Exists with ID: "+showId);
		
		if(movieRepository.existsById(movie.getMovieId()))
			throw new MovieNotFoundException("Movie Already Exists with ID: "+movie.getMovieId());
		
		Show show=new Show();
		show=iShowRepository.findById(showId).get();
		movie.setShow(show);
		show.setMovie(movie);

		movieRepository.saveAndFlush(movie);
		
	}
	
	
	@Override
	public void updateMovie(Movie movie) throws MovieNotFoundException
	{
		if(!movieRepository.existsById(movie.getMovieId()))
			throw new MovieNotFoundException("Movie Doesn't Exists with ID: "+movie.getMovieId());
		
		movieRepository.findById(movie.getMovieId()).get();
		movieRepository.saveAndFlush(movie);
		
	}

	@Override
	public Movie getMovieDetails(int id) throws MovieNotFoundException 
	{
		if(!movieRepository.existsById(id))
			throw new MovieNotFoundException("Movie Already Exists with ID: "+id);
		
		 return movieRepository.findById(id).get();
		
	}

	
	@Override
	public List<Theater> getMovieByCity(String city) 
	{
		
		return iTheaterRepository.getMovieByTheaterCity(city);
	}
	
	
	@Override
	public List<Movie> getAllTheMovieDetails() throws MovieNotFoundException 
	{
		List<Movie> movieBean=movieRepository.findAll();
		if(movieBean.size()==0)
			throw new MovieNotFoundException("No Movie Exists..!! ");
		
		return movieRepository.findAll();
	}

	@Override
	public void removeMovie(int id) 
	{
		logger.info("/---------- Movie Deleted Successfully ----------/");
		movieRepository.deleteById(id);
		
	}


	

	

	
	
}
