package com.merin.moviebooking.service;

import java.util.List;
import com.merin.moviebooking.entity.Theater;
import com.merin.moviebooking.exception.ShowNotFoundException;
import com.merin.moviebooking.exception.TheaterNotFoundException;

public interface ITheaterService 
{

	public void addTheater(Theater theater) throws TheaterNotFoundException;

	public void updateTheater(Theater theater) throws TheaterNotFoundException;

	public Theater getTheaterDetails(int id) throws TheaterNotFoundException;

	public List<Theater> getAllTheaters() throws TheaterNotFoundException;

	public void removeTheater(int id) throws TheaterNotFoundException;

	public void deleteAllTheTheaters() throws TheaterNotFoundException;

	public List<Theater> getShowsByTheaterName(String name) throws ShowNotFoundException, TheaterNotFoundException ;


}
