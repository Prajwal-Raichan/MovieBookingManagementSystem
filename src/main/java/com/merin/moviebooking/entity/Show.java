package com.merin.moviebooking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Show_Details")
public class Show
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Show_ID")
	private int showId;

	@Column(name = "Show_Name")
	private String showName;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "Show_StartTime")
	private LocalDateTime showStartTime;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "Show_EndTime")
	private LocalDateTime showEndTime;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "Show_Date")
	private LocalDate showDate;
	
	@OneToOne
	private Movie movie;

	@JsonIgnore
	@ManyToOne
	private Theater theater;
	
	@JsonIgnore
	@OneToOne
	private Booking booking;

	@JsonIgnore
	@ManyToOne
	private Screen screen;
	
	public Show()  
	{
		super();
	}
	
	
	public Show(int showId, String showName, LocalDateTime showStartTime, LocalDateTime showEndTime, LocalDate showDate,
			Movie movie, Theater theater, Booking booking, Screen screen) 
	{
		super();
		this.showId = showId;
		this.showName = showName;
		this.showStartTime = showStartTime;
		this.showEndTime = showEndTime;
		this.showDate = showDate;
		this.movie = movie;
		this.theater = theater;
		this.booking = booking;
		this.screen = screen;
	}
 
	

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public LocalDateTime getShowStartTime() {
		return showStartTime;
	}

	public void setShowStartTime(LocalDateTime showStartTime) {
		this.showStartTime = showStartTime;
	}

	public LocalDateTime getShowEndTime() {
		return showEndTime;
	}

	public void setShowEndTime(LocalDateTime showEndTime) {
		this.showEndTime = showEndTime;
	}

	public LocalDate getShowDate() {
		return showDate;
	}

	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	/**@JsonIgnore
	public int getTheaterNumber()
	{
		if(theater==null)
			theater=new Theater();
		return theater.getTheaterId();
	}**/
	

}
