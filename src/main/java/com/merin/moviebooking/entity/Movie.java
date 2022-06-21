package com.merin.moviebooking.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Movie_Details")
public class Movie                
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Movie_Id")
	private int movieId;
	
	@Column(name = "Movie_Name")
	private String movieName;
	
	@Column(name = "Movie_Genre")
	private String movieGenre;
	
	@Column(name = "Movie_Language")
	private String movieLanguage;
	
	@Column(name = "Movie_Hours")
	private String movieHours;
	
	@JsonFormat(pattern="yyyy-MM-dd")        //Java=>JSON Object
	@Column(name = "Movie_Date")
	private LocalDate movieDate;
	
	@Column(name = "Movie_Rating")
	private String movieRating;
	
	@JsonIgnore
	@OneToOne
	private Show show;
	
	
	public Movie() 
	{
		super();
	}

	
	public Movie(int movieId, String movieName, String movieGenre, String movieLanguage, String movieHours,
			LocalDate movieDate, String movieRating, Show show) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieGenre = movieGenre;
		this.movieLanguage = movieLanguage;
		this.movieHours = movieHours;
		this.movieDate = movieDate;
		this.movieRating = movieRating;
		this.show = show;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public String getMovieLanguage() {
		return movieLanguage;
	}

	public void setMovieLanguage(String movieLanguage) {
		this.movieLanguage = movieLanguage;
	}

	public String getMovieHours() {
		return movieHours;
	}

	public void setMovieHours(String movieHours) {
		this.movieHours = movieHours;
	}

	public LocalDate getMovieDate() {
		return movieDate;
	}

	public void setMovieDate(LocalDate movieDate) {
		this.movieDate = movieDate;
	}

	public String getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

}
