package com.merin.moviebooking.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merin.moviebooking.entity.Screen;
import com.merin.moviebooking.entity.Show;
import com.merin.moviebooking.entity.Theater;
import com.merin.moviebooking.exception.ScreenNotFoundException;
import com.merin.moviebooking.exception.TheaterNotFoundException;
import com.merin.moviebooking.repository.IScreenRepository;
import com.merin.moviebooking.repository.IShowRepository;
import com.merin.moviebooking.repository.ITheaterRepository;

@Service
public class IScreenServiceImpl implements IScreenService
{
	
	@Autowired
	private IScreenRepository screenRepository;
	
	@Autowired
	private ITheaterRepository iTheaterRepository;
	
	@Autowired
	private IShowRepository iShowRepository;
	
	
	@Override
	public void addScreen(Screen screen) throws ScreenNotFoundException
	{
		if(screenRepository.existsById(screen.getScreenId()))
			throw new ScreenNotFoundException("Screen Doesn't Exists with ID: "+screen.getScreenId());
		
		screenRepository.saveAndFlush(screen);
		
	}
	
	
	@Override
	public void addScreenToTheater(Screen screen,Integer theaterId,Integer showId) throws TheaterNotFoundException, ScreenNotFoundException 
	{	
		if(!iTheaterRepository.existsById(theaterId))
			throw new TheaterNotFoundException("Theater Doesn't Exists with ID: "+theaterId);
		
		if(!iShowRepository.existsById(showId))
			throw new ScreenNotFoundException("Show Doesn't Exists with ID: "+showId);
		
		Theater theater=new Theater();
		Show show=new Show();
		List<Screen> screenBean=new ArrayList<Screen>();
		List<Show> showBean=new ArrayList<Show>();
		
		theater=iTheaterRepository.findById(theaterId).get();
		show=iShowRepository.findById(showId).get();
		
		screenBean.add(screen);
		showBean.add(show);
		
		if(screenRepository.existsById(screen.getScreenId()))
			throw new ScreenNotFoundException("Screen Already Exists In the Theater.!!");
		
		
		screen.setShow(showBean);
		screen.setTheater(theater);
		theater.setScreen(screenBean);
		show.setTheater(theater);
		show.setScreen(screen);
		theater.setShow(showBean);
		
		screenRepository.saveAndFlush(screen);	
		
	}

	@Override
	public void updateScreen(Screen screen) throws ScreenNotFoundException 
	{
		if(!screenRepository.existsById(screen.getScreenId()))
			throw new ScreenNotFoundException("Screen Doesn't Exists In the Theater.!!");
		
		screenRepository.findById(screen.getScreenId()).get();
		screenRepository.saveAndFlush(screen);
	}

	@Override
	public Screen getScreenDetails(int id) throws ScreenNotFoundException 
	{
		if(!screenRepository.existsById(id))
		throw new ScreenNotFoundException("Screen Doesn't Exists In the Theater.!!");
		
		return screenRepository.findById(id).get();
	}

	@Override
	public List<Screen> getAllTheScreenDetails() throws ScreenNotFoundException
	{
		List<Screen> showBean=screenRepository.findAll();
		if(showBean.size()==0)
			throw new ScreenNotFoundException("No Screens Exists in the Theater.!!");
		
		return screenRepository.findAll();
	}

	@Override
	public void deleteScreenById(int id) throws ScreenNotFoundException
	{
		if(!screenRepository.existsById(id))
			throw new ScreenNotFoundException("Screen Doesn't Exists In the Theater.!!");
		
		screenRepository.deleteById(id);
		
	}

	

}
