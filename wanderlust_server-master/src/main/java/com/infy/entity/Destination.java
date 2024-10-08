package com.infy.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//Entity Component
@Entity		

//Representing the Table Destination present in the Database
@Table(name = "destination") 				

public class  Destination{
	
	//Primary Key
	@Id 									
	private String destinationId;
	private String continent;
	private String destinationName;
	private String imageUrl;
	private Integer noOfNights;
	private Float flightCharge;
	private Float chargePerPerson;
	private Float discount;
	private Integer availability;
	
	//One to One Relation with the Table Details on detail_id
	@OneToOne(cascade = CascadeType.ALL)  	
	@JoinColumn(name = "details_id")		
	private Details details;
	
	//generate getters and setters
	public String getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getNoOfNights() {
		return noOfNights;
	}
	public void setNoOfNights(Integer noOfNights) {
		this.noOfNights = noOfNights;
	}
	public float getFlightCharge() {
		return flightCharge;
	}
	public void setFlightCharge(float flightCharge) {
		this.flightCharge = flightCharge;
	}
	public float getChargePerPerson() {
		return chargePerPerson;
	}
	public void setChargePerPerson(float chargePerPerson) {
		this.chargePerPerson = chargePerPerson;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public Integer getAvailability() {
		return availability;
	}
	public void setAvailability(Integer availability) {
		this.availability = availability;
	}
	public Details getDetails() {
		return details;
	}
	public void setDetails(Details details) {
		this.details = details;
	}
}