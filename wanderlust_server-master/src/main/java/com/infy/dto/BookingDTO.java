package com.infy.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;

public class BookingDTO {
	@NotNull(message="{bookingId.absent}")
	private Integer bookingId;
	@NotNull(message="{checkIn.absent}")
	@Future(message="{checkIn.invalid}")
	private LocalDate checkIn;
	@NotNull(message="{checkOut.absent}")
	private LocalDate checkOut;
	@NotNull(message="{noOfPeople.absent}")
	@Min(value=1,message="{noOfPeople.invalid}")
	@Max(value=5,message="{noOfPeople.invalid}")
	private Integer noOfPeople;
	@NotNull(message="{totalCost.absent}")
	private Float totalCost;
	@NotNull(message="{timeOfBooking.absent}")
	private Timestamp timeOfBooking;
	private DestinationDTO destination;
	private UserDTO user;
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public LocalDate getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}
	public LocalDate getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	public Integer getNoOfPeople() {
		return noOfPeople;
	}
	public void setNoOfPeople(Integer noOfPeople) {
		this.noOfPeople = noOfPeople;
	}
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	public Timestamp getTimeOfBooking() {
		return timeOfBooking;
	}
	public void setTimeOfBooking(Timestamp timeOfBooking) {
		this.timeOfBooking = timeOfBooking;
	}
	public DestinationDTO getDestination() {
		return destination;
	}
	public void setDestination(DestinationDTO destination) {
		this.destination = destination;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}

}
