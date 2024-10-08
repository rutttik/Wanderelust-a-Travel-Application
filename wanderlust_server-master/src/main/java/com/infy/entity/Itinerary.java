package com.infy.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//Entity Component
@Entity  

//Representing the Table Itinerary present in the Database
@Table(name = "ITINERARY") 						

public class Itinerary{
	
	//Primary Key
	@Id 										
	private String itineraryId;
	private String firstDay;
	private String restOfDays;
	private String lastDay;
	
	//generate getters and setters
	public String getItineraryId() {
		return itineraryId;
	}
	public void setItineraryId(String itineraryId) {
		this.itineraryId = itineraryId;
	}
	public String getFirstDay() {
		return firstDay;
	}
	public void setFirstDay(String firstDay) {
		this.firstDay = firstDay;
	}
	public String getRestOfDays() {
		return restOfDays;
	}
	public void setRestOfDays(String restOfDays) {
		this.restOfDays = restOfDays;
	}
	public String getLastDay() {
		return lastDay;
	}
	public void setLastDay(String lastDay) {
		this.lastDay = lastDay;
	}
	
	
}