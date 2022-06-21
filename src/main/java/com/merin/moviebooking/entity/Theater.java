package com.merin.moviebooking.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Theater_Details")
public class Theater 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Theater_Id")
	private int theaterId;
	
	@Column(name = "Theater_Name",unique = true)
	private String theaterName;
	
	@Column(name = "Theater_City")
	private String theaterCity;
	
	@Column(name = "Theater_Manager")
	private String managerName;
	
	@Column(name = "Manager_Contact",unique = true)
	private long managerContact;                          
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Show> show;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Screen> screen;
	
	
	public Theater() 
	{
		super();
		
	}


	public Theater(int theaterId, String theaterName, String theaterCity, String managerName, long managerContact,
			List<Show> show, List<Screen> screen) {
		super();
		this.theaterId = theaterId;
		this.theaterName = theaterName;
		this.theaterCity = theaterCity;
		this.managerName = managerName;
		this.managerContact = managerContact;
		this.show = show;
		this.screen = screen;
	}


	public int getTheaterId() {
		return theaterId;
	}


	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}


	public String getTheaterName() {
		return theaterName;
	}


	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}


	public String getTheaterCity() {
		return theaterCity;
	}


	public void setTheaterCity(String theaterCity) {
		this.theaterCity = theaterCity;
	}


	public String getManagerName() {
		return managerName;
	}


	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}


	public long getManagerContact() {
		return managerContact;
	}


	public void setManagerContact(long managerContact) {
		this.managerContact = managerContact;
	}


	public List<Show> getShow() {
		return show;
	}


	public void setShow(List<Show> show) {
		this.show = show;
	}


	public List<Screen> getScreen() {
		return screen;
	}


	public void setScreen(List<Screen> screen) {
		this.screen = screen;
	}

	
}
