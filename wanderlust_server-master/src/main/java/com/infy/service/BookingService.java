package com.infy.service;

import java.util.List;

import com.infy.dto.BookingDTO;
import com.infy.entity.Booking;
import com.infy.exception.WanderLustException;

public interface BookingService {
	public String bookPackage(BookingDTO booking) throws WanderLustException ;
	
	public List<Booking> viewBookings(Integer userId) throws Exception;

	

}