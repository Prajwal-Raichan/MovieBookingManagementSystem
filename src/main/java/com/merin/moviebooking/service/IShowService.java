package com.merin.moviebooking.service;

import java.util.List;

import com.merin.moviebooking.entity.Show;
import com.merin.moviebooking.exception.ScreenNotFoundException;
import com.merin.moviebooking.exception.ShowNotFoundException;
import com.merin.moviebooking.exception.TheaterNotFoundException;

public interface IShowService 
{

	public void addShow(Show show) throws ShowNotFoundException;
	
	public void addShowToTheater(Show show,Integer theaterId,Integer screenId) throws ShowNotFoundException, TheaterNotFoundException, ScreenNotFoundException;

	public void updateShow(Show show) throws ShowNotFoundException;

	public Show viewShowDetails(int id) throws ShowNotFoundException;

	public List<Show> getAllShowDetails() throws ShowNotFoundException;

	public void removeShowById(int id) throws ShowNotFoundException;

	public void deleteAllTheShows() throws ShowNotFoundException;

	
	

}
