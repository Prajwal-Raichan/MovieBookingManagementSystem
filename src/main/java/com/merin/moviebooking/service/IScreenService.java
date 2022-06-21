package com.merin.moviebooking.service;

import java.util.List;
import com.merin.moviebooking.entity.Screen;
import com.merin.moviebooking.exception.ScreenNotFoundException;
import com.merin.moviebooking.exception.TheaterNotFoundException;

public interface IScreenService 
{

	public void addScreen(Screen screen) throws ScreenNotFoundException;
	
	public void addScreenToTheater(Screen screen, Integer theaterId, Integer showId) throws TheaterNotFoundException, ScreenNotFoundException;
	
	public void updateScreen(Screen screen) throws ScreenNotFoundException ;
	
	public Screen getScreenDetails(int id) throws ScreenNotFoundException ;
	
	public List<Screen> getAllTheScreenDetails() throws ScreenNotFoundException;

	public void deleteScreenById(int id) throws ScreenNotFoundException;

	


}
