package com.infy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.dto.BookingDTO;
import com.infy.entity.Booking;
import com.infy.service.BookingService;

@CrossOrigin

@RestController

@RequestMapping("/BookingAPI")

public class BookingAPI {
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private Environment environment;
	
	@RequestMapping(value="/booking", method = RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestBody BookingDTO booking) throws Exception
	{
		try {
			String destinationNamme = bookingService.bookPackage(booking);
			String successMessage = "Congratulations "+ booking.getUser().getUserName()+"! Your Trip to "+destinationNamme+"has been booked sucessfully";
			ResponseEntity<String> response=new ResponseEntity<String>(successMessage,HttpStatus.CREATED);
			return response;
		}catch(Exception e)
		{
			String message=environment.getProperty(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,message,e);
		}
	}
	//Using Get Mapping and mapping the URL to /view Bookings of the user Id provided
		@GetMapping(value="/viewBookings/{userId}")
		public ResponseEntity<List<Booking>> getViewBooking(@PathVariable Integer userId) throws Exception{
			try{
				List<Booking> booking = bookingService.viewBookings(userId);
	    // fetching the list of all bookings
				ResponseEntity <List<Booking>> response = new ResponseEntity <List<Booking>>(booking, HttpStatus.OK);

				return response;
			}
				catch(Exception e){
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
				}	

		}
	
	

}
