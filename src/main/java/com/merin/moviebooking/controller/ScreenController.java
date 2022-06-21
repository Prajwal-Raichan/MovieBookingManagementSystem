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
import com.merin.moviebooking.entity.Screen;
import com.merin.moviebooking.exception.ScreenNotFoundException;
import com.merin.moviebooking.exception.TheaterNotFoundException;
import com.merin.moviebooking.service.IScreenService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/screen")
@Tag(name = "Screen Controller",description = "Screen Management Portal")
public class ScreenController 
{
	Logger logger=LoggerFactory.getLogger(ScreenController.class);
	
	@Autowired
	private IScreenService screenService;
	
	
	@PostMapping("/addScreen")
	public HttpStatus insertScreen(@RequestBody Screen screen) throws ScreenNotFoundException
	{
		
		screenService.addScreen(screen);
		
		logger.info("/---------- Screen Added Successfully ----------/");
		return HttpStatus.CREATED;
	}
	
	
	
	
	@PostMapping("/addScreen/{theaterId}/{showId}")
	public HttpStatus insertScreenToTheater(@RequestBody Screen screen,@PathVariable("theaterId")Integer theaterId, 
			@PathVariable("showId")Integer showId) throws ScreenNotFoundException, TheaterNotFoundException
	{
		screenService.addScreenToTheater(screen, theaterId,showId);
		logger.info("/---------- Screen Added Successfully ----------/");
		return HttpStatus.CREATED;
		
	}
	
	
	@PutMapping("/updateScreen")
	public ResponseEntity<Boolean> modifyScreen(@RequestBody Screen screen) throws ScreenNotFoundException 
	{
		screenService.updateScreen(screen);
		logger.info("/---------- Screen Updated Successfully ----------/");
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/viewScreenById/{id}")
	public Screen getScreenDetails(@PathVariable("id") int id) throws ScreenNotFoundException 
	{
		logger.info("/---------- Fetched Screen Details Successfully ----------/");
		return screenService.getScreenDetails(id);
		
	}
	
	
	@GetMapping("/viewAllTheScreens")
	public List<Screen> getAllTheScreenDetails() throws ScreenNotFoundException
	{
		logger.info("/---------- Fetched All The Screen Details Successfully ----------/");
		return screenService.getAllTheScreenDetails();
		
	}
	
	
	@DeleteMapping("/deleteScreenById/{id}")
	public ResponseEntity<Boolean> removeScreen(@PathVariable("id") int id) throws ScreenNotFoundException
	{
		screenService.deleteScreenById(id);
		logger.info("/---------- Deleted The Seat Successfully ----------/");
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		
	}
	

}
