package com.merin.moviebooking.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Screen_Details")
public class Screen 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Screen_Id")
	private int screenId;
	
	@Column(name = "Screen_Name")
	private String screenName;
	
	@Column(name = "Seat_Columns")
	private int seatColumns;
	
	@Column(name = "Seat_Rows")
	private int seatRows;

	@JsonIgnore
	@ManyToOne
	private Theater theater;
	
	@JsonIgnore
	@OneToMany
	private List<Show> show;
	
	public Screen() 
	{
		super();
	}

	public Screen(int screenId, String screenName, int seatColumns, int seatRows, Theater theater, List<Show> show) {
		super();
		this.screenId = screenId;
		this.screenName = screenName;
		this.seatColumns = seatColumns;
		this.seatRows = seatRows;
		this.theater = theater;
		this.show = show;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public int getSeatColumns() {
		return seatColumns;
	}

	public void setSeatColumns(int seatColumns) {
		this.seatColumns = seatColumns;
	}

	public int getSeatRows() {
		return seatRows;
	}

	public void setSeatRows(int seatRows) {
		this.seatRows = seatRows;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public List<Show> getShow() {
		return show;
	}

	public void setShow(List<Show> show) {
		this.show = show;
	}

	
}
