package com.merin.moviebooking.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merin.moviebooking.entity.Screen;
import com.merin.moviebooking.entity.Show;
import com.merin.moviebooking.entity.Theater;
import com.merin.moviebooking.exception.ScreenNotFoundException;
import com.merin.moviebooking.exception.ShowNotFoundException;
import com.merin.moviebooking.exception.TheaterNotFoundException;
import com.merin.moviebooking.repository.IScreenRepository;
import com.merin.moviebooking.repository.IShowRepository;
import com.merin.moviebooking.repository.ITheaterRepository;

@Service
public class IShowServiceImpl implements IShowService
{
	Logger logger=LoggerFactory.getLogger(IShowServiceImpl.class);

	@Autowired
	private IShowRepository showRepository;
	
	@Autowired
	private ITheaterRepository iTheaterRepository;
	
	@Autowired
	private IScreenRepository iScreenRepository;
	

	
	@Override
	public void addShow(Show show) throws ShowNotFoundException 
	{
		if(showRepository.existsById(show.getShowId()))
			throw new ShowNotFoundException("Show Already Exists In the Theater:");
		
		showRepository.saveAndFlush(show);
		
	}
	
	
	@Override
	public void addShowToTheater(Show show, Integer theaterId, Integer screenId) throws ShowNotFoundException, TheaterNotFoundException, ScreenNotFoundException
	{
		
		if(!iTheaterRepository.existsById(theaterId))
			throw new TheaterNotFoundException("No Theater Exists With ID: "+theaterId);
		
		if(!iScreenRepository.existsById(screenId))
			throw new ScreenNotFoundException("No Screen Exists With ID: "+screenId);
		
		Theater theater=new Theater();
		Screen screen=new Screen();
		List<Show> showBean=new ArrayList<>();      /**[Single Theater Entity Cannot store List directly with the help of ArrayList(Implementation Object) we can store.*/
		List<Screen> screenBean=new ArrayList<>();
		
		theater=iTheaterRepository.findById(theaterId).get();
		screen=iScreenRepository.findById(screenId).get();
		
		show.setTheater(theater);
		show.setScreen(screen);
		showBean.add(show);
		screenBean.add(screen);
		
		for(Show showRepo:showRepository.findAll())
		{
			if(showRepo.getTheater()==theater)
				showBean.add(showRepo);
		}
		
		for(Screen ScreenRepo:iScreenRepository.findAll())
		{
			if(ScreenRepo.getTheater()==theater)
				screenBean.add(ScreenRepo);
		}
		
		theater.setShow(showBean);
		theater.setScreen(screenBean); 
		
		showRepository.saveAndFlush(show);
		
		
	}

	
	@Override
	public void updateShow(Show show) throws ShowNotFoundException
	{
		if(!showRepository.existsById(show.getShowId()))
			throw new ShowNotFoundException("Show Doesn't Exists In the Theater:");
		
		showRepository.findById(show.getShowId()).get();
		showRepository.saveAndFlush(show);
	}


	@Override
	public Show viewShowDetails(int id) throws ShowNotFoundException
	{
		if(!showRepository.existsById(id))
			throw new ShowNotFoundException("Show Doesn't Exists In the Theater:");
		
		return showRepository.findById(id).get();
		
	}


	@Override
	public List<Show> getAllShowDetails() throws ShowNotFoundException
	{
		List<Show> showBean=showRepository.findAll();
		if(showBean.size()==0)
			throw new ShowNotFoundException("No Shows Exists in the Application:");
		
		return showRepository.findAll();
	}


	@Override
	public void removeShowById(int id) throws ShowNotFoundException
	{
		if(!showRepository.existsById(id))
			throw new ShowNotFoundException("Show Doesn't Exists In the Theater to Remove:");
		
		showRepository.deleteById(id);
		
	}


	@Override
	public void deleteAllTheShows() throws ShowNotFoundException
	{
		List<Show> showBean=showRepository.findAll();
		if(showBean.size()==0)
			throw new ShowNotFoundException("No Shows Exists in the Application To Remove:");
		
		showRepository.deleteAll();
		
	}


	
	
	
}
