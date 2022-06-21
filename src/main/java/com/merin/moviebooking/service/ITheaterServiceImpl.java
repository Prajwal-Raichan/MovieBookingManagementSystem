package com.merin.moviebooking.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merin.moviebooking.entity.Show;
import com.merin.moviebooking.entity.Theater;
import com.merin.moviebooking.exception.ShowNotFoundException;
import com.merin.moviebooking.exception.TheaterNotFoundException;
import com.merin.moviebooking.repository.IShowRepository;
import com.merin.moviebooking.repository.ITheaterRepository;

@Service
public class ITheaterServiceImpl implements ITheaterService
{
	
	@Autowired
	private ITheaterRepository theaterRepository;

	@Autowired
	private IShowRepository iShowRepository;
	
	@Override
	public void addTheater(Theater theater) throws TheaterNotFoundException
	{
		if(theaterRepository.existsByTheaterName(theater.getTheaterName()))
			throw new TheaterNotFoundException(theater.getTheaterId()+" :Theater Already Exists");
		
		 theaterRepository.saveAndFlush(theater);
		
	}


	@Override
	public void updateTheater(Theater theater) throws TheaterNotFoundException
	{
		if(!theaterRepository.existsById(theater.getTheaterId()))
			throw new TheaterNotFoundException("Theater Dosen't Exists with Id:"+theater.getTheaterId());
		
		theaterRepository.findById(theater.getTheaterId()).get();
		theaterRepository.saveAndFlush(theater);
		
	}


	@Override
	public Theater getTheaterDetails(int id) throws TheaterNotFoundException
	{
		if(!theaterRepository.existsById(id))
			throw new TheaterNotFoundException("Theater Dosen't Exists with Id:"+id);
		
		return theaterRepository.findById(id).get();
		
	}


	@Override
	public List<Theater> getAllTheaters() throws TheaterNotFoundException
	{
		List<Theater> theaterBean=theaterRepository.findAll();
		if(theaterBean.size()==0)
			throw new TheaterNotFoundException("No Theater Exists in the Application:");
		
		return theaterRepository.findAll();
	}


	@Override
	public void removeTheater(int id) throws TheaterNotFoundException
	{
		if(!theaterRepository.existsById(id))
			throw new TheaterNotFoundException("Theater Dosen't Exists with Id:"+id);
		
		theaterRepository.deleteById(id);
	}


	@Override
	public void deleteAllTheTheaters() throws TheaterNotFoundException
	{
		List<Theater> theaterBean=theaterRepository.findAll();
		if(theaterBean.size()==0)
			throw new TheaterNotFoundException("No Theater Exists in the Application:");
		
		theaterRepository.deleteAll();
		
	}


	@Override
	public List<Theater> getShowsByTheaterName(String name) throws ShowNotFoundException,TheaterNotFoundException
	{
		List<Theater> theaterBean=theaterRepository.findAll();
		List<Show> showBean=iShowRepository.findAll();
		if(showBean.size()==0)
			throw new ShowNotFoundException("No Shows Exists in the Theater:");
		if(theaterBean.size()==0)
			throw new TheaterNotFoundException("No Theater Exists in the Application:");
		
		return theaterRepository.getShowsByTheaterName(name);
		
	}


}
