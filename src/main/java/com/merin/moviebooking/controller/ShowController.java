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
import com.merin.moviebooking.entity.Show;
import com.merin.moviebooking.entity.Theater;
import com.merin.moviebooking.exception.ScreenNotFoundException;
import com.merin.moviebooking.exception.ShowNotFoundException;
import com.merin.moviebooking.exception.TheaterNotFoundException;
import com.merin.moviebooking.service.IShowService;
import com.merin.moviebooking.service.ITheaterService;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/show")
@Tag(name = "Show Controller",description = "Show Managed Portal")
public class ShowController
{
	
	Logger logger=LoggerFactory.getLogger(ShowController.class);
	
	@Autowired
	private IShowService showService;
	
	@Autowired
	private ITheaterService iTheaterService;
	
	
	/** Admin adds the Show **/
	@PostMapping("/addShow")
	public HttpStatus addShow(@RequestBody Show show) throws ShowNotFoundException
	{
		
		logger.info("/---------- Show Added Successfully ----------/");
		showService.addShow(show);
		return HttpStatus.CREATED;
	}
	
	
	/** Admin adds the Show to the Theater **/
	@PostMapping("/addShow/{theaterId}/{screenId}")
	public HttpStatus insertShowToTheater(@RequestBody Show show,@PathVariable("theaterId") Integer theaterId,
			 @PathVariable("screenId") Integer screenId) throws ShowNotFoundException,TheaterNotFoundException, ScreenNotFoundException
	{
		
		logger.info("/---------- Show Added Successfully ----------/");
		showService.addShowToTheater(show,theaterId,screenId);
		return HttpStatus.CREATED;
	}
	

	/** Admin updates the Show **/
	@PutMapping("/updateShow")
	public ResponseEntity<Boolean> modifyShow(@RequestBody Show show) throws ShowNotFoundException
	{
		
		logger.info("/---------- Show Updated Successfully ----------/");
		showService.updateShow(show);
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
	}
	
	
	/** Customer views the Show By ID**/
	@GetMapping("/viewShowById/{id}") 
	public ResponseEntity<Show> getShowDetailsById(@PathVariable("id") int id) throws ShowNotFoundException
	{
		
		Show show=showService.viewShowDetails(id);
		logger.info("/---------- Show Fetched Successfully ----------/");
		return new ResponseEntity<Show>(show,HttpStatus.OK);
		
	}
	
	/** Customer views the Show By TheaterName**/
	@GetMapping("/viewShowsByTheaterName/{name}")
	public List<Theater> viewShowsByTheaterName(@PathVariable("name") String name) throws ShowNotFoundException, TheaterNotFoundException
	{
		
		logger.info("/---------- Fetched All The Shows in Theater "+name+" Successfully----------/");
		return iTheaterService.getShowsByTheaterName(name);
		
	}
	
	
	/** Customer views all the Show**/
	@GetMapping("/viewAllTheShows")
	public List<Show> getAllShowDetails() throws ShowNotFoundException
	{
		
		logger.info("/---------- Fetched All The Shows Successfully ----------/");
		return showService.getAllShowDetails();
		
	}
	
	
	/** Admin removes the Show By Id **/
	@DeleteMapping("removeShowById/{id}") 
	public ResponseEntity<Boolean> deleteShowById(@PathVariable("id") int id) throws ShowNotFoundException
	{
		
		showService.removeShowById(id);
		logger.info("/---------- Deleted The Show Successfully ----------/");
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		
	}
	
	/** Admin deletes all the Show**/
	@DeleteMapping("/deleteaAllTheShows")
	public ResponseEntity<Boolean> removeAllTheShows() throws ShowNotFoundException
	{
		showService.deleteAllTheShows();
		logger.info("/---------- Removed All The Shows Successfully ----------/");
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		
		
	}
	
	
}
