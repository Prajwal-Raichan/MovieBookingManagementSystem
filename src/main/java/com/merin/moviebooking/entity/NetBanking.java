package com.merin.moviebooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NetBanking_Details")
public class NetBanking 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NetBankingId")
	private int nId;
	
	@Column(name = "NetBankingLogin_Id")
	private String netLoginId;
	
	
	@Column(name = "NetBanking_Password")
	private String netPassword;
	
	
	public NetBanking() 
	{
		super();
	}


	public NetBanking(int nId, String netLoginId, String netPassword) 
	{
		super();
		this.nId = nId;
		this.netLoginId = netLoginId;
		this.netPassword = netPassword;
	}


	public int getnId() {
		return nId;
	}


	public void setnId(int nId) {
		this.nId = nId;
	}


	public String getNetLoginId() {
		return netLoginId;
	}


	public void setNetLoginId(String netLoginId) {
		this.netLoginId = netLoginId;
	}


	public String getNetPassword() {
		return netPassword;
	}


	public void setNetPassword(String netPassword) {
		this.netPassword = netPassword;
	}
	

}
