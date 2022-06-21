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
import com.merin.moviebooking.entity.Movie;
import com.merin.moviebooking.entity.Theater;
import com.merin.moviebooking.exception.MovieNotFoundException;
import com.merin.moviebooking.exception.ShowNotFoundException;
import com.merin.moviebooking.service.IMovieService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/movie")
@Tag(name = "Movie Controller",description = "Movie Management Portal")
public class MovieController       
{

	Logger logger=LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	private IMovieService movieService;
	
	
	/** Admin adds the Movie to the Show **/
	@PostMapping("/addMovie/{showId}")
	public HttpStatus insertMovieToShow(@RequestBody Movie movie,@PathVariable("showId") Integer showId) throws MovieNotFoundException, ShowNotFoundException
	{
		
		logger.info("/---------- Movie Added Successfully ----------/");
		movieService.addMovieToShow(movie,showId);
		return HttpStatus.CREATED;
		
	}
	
	/** Admin updates the Movie **/
	@PutMapping("/updateMovie")
	public ResponseEntity<Boolean> modifyMovie(@RequestBody Movie movie) throws MovieNotFoundException
	{
		
		logger.info("/---------- Movie Updated Successfully ----------/");
		movieService.updateMovie(movie);
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		
	}
	
	/** Customer views the Movie By Id **/
	@GetMapping("/viewMovieById/{id}")
	public Movie getMovieDetails(@PathVariable("id") int id) throws MovieNotFoundException
	{
		logger.info("/---------- Fetched The Movie Successfully ----------/");
		return movieService.getMovieDetails(id);
		
	}
	      
	
	/** Customer views the Movie By City **/
	@GetMapping("/viewMovieByCity/{city}")
	public List<Theater> getMovieByCity(@PathVariable("city")String city)
	{
		return movieService.getMovieByCity(city);
	}
	
	
	/** Customer views all the Movies **/
	@GetMapping("/viewAllTheMovies")
	public List<Movie> getAllTheMovieDetails() throws MovieNotFoundException 
	{
		
		logger.info("/---------- Fetched All the Movies  Successfully ----------/");
		return movieService.getAllTheMovieDetails();
	}
	
	
	/** Customer deletes the Movie By Id **/
	@DeleteMapping("/deleteTheMovieById/{id}")
	public ResponseEntity<Boolean> removeMovie(@PathVariable("id") int id) 
	{
		movieService.removeMovie(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
		
	}
	
	
}


























  /** 
   * @PostMapping("/addMovie")
   * @ApiOperation(value = "Insert Movie",notes = "Hello")
   * @ApiResponses(value = {
   * @ApiResponse(code = 200,message = "Added Movie Successfully")
   * @ApiResponse(code = 404,message = "Invalid"),
   * @ApiResponse(code = 500,message = "Internal Server Error")})
   */
