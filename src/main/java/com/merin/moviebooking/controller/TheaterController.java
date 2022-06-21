package com.merin.moviebooking.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.merin.moviebooking.entity.Theater;
import com.merin.moviebooking.exception.TheaterNotFoundException;
import com.merin.moviebooking.service.ITheaterService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/theater")
@Tag(name = "Theater Controller",description = "Theater Managed Portal")
public class TheaterController 
{
	
	Logger logger=LoggerFactory.getLogger(TheaterController.class);
	
	@Autowired
	private ITheaterService theaterService;
	
	
	/** Admin adds the Theater **/
	@PostMapping("/insertTheater")
	public HttpStatus addTheater(@RequestBody Theater theater) throws TheaterNotFoundException  
	{
	
		logger.info("/---------- Theater Added Successfully ----------/");
		theaterService.addTheater(theater); 
		return HttpStatus.CREATED;	
	}
	
	
	/** Admin Updates the Theater **/
	@PutMapping("/updateTheater")
	public ResponseEntity<Boolean> modifyTheater(@RequestBody Theater theater) throws TheaterNotFoundException
	{
		
		theaterService.updateTheater(theater);
		logger.info("/---------- Theater Updated Successfully ----------/");
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		
	}
	
	
	/** Customer views the Theater By Id **/
	@GetMapping("/viewTheaterById/{id}")
	public Theater viewTheaterById(@PathVariable("id") int id) throws TheaterNotFoundException
	{
		
		logger.info("/---------- Theater Fetched Successfully ----------/");
		return theaterService.getTheaterDetails(id);
	}
	
	
	/** Customer views All the Theater **/
	@GetMapping("/viewAllTheaters")
	public List<Theater> getAllTheaters() throws TheaterNotFoundException
	{
		
		logger.info("/---------- Fetched All The Theaters Successfully ----------/");
		return theaterService.getAllTheaters();
		
	}
	
	

	@DeleteMapping("/deleteTheater/{id}")
	public ResponseEntity<Boolean> deleteTheater(@PathVariable("id") int id) throws TheaterNotFoundException
	{
		
		theaterService.removeTheater(id);
		logger.info("/---------- Theater Deleted Successfully ----------/");
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/deleteaAllTheTheaters")
	public ResponseEntity<Boolean> removeAllTheTheaters() throws TheaterNotFoundException
	{
		
		logger.info("/---------- Deleted All The Theaters Successfully ----------/");
		theaterService.deleteAllTheTheaters();
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		
	}
	
	
}
