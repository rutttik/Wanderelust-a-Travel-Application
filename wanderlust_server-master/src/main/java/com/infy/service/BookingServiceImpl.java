package com.infy.service;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.BookingDTO;
import com.infy.entity.Booking;
import com.infy.entity.Destination;
import com.infy.entity.User;
import com.infy.exception.WanderLustException;
import com.infy.repository.BookingRepository;
import com.infy.repository.PackageRepository;
import com.infy.repository.UserRepository;

//Component is a Service component
//Annotating the component to handle Transactions
@Service(value="bookingService")
@Transactional
public class BookingServiceImpl implements BookingService{

	
	
	@Autowired
private BookingRepository bookingRepository;
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	
	@Override
	// Method to book a Package
	public String bookPackage(BookingDTO booking) throws WanderLustException {
		
		Booking bookingEntity=new Booking();
		bookingEntity.setCheckIn(booking.getCheckIn());
		bookingEntity.setCheckOut(booking.getCheckOut());
		bookingEntity.setNoOfPeople(booking.getNoOfPeople());
		bookingEntity.setTotalCost(booking.getTotalCost());
		
		
		Optional<Destination> optional=packageRepository.findById(booking.getDestination().getDestinationId());
		Destination destination=optional.orElseThrow(()-> new WanderLustException("PackageService.DESTINATION_NOT_FOUND"));

		
		Optional<User> optional1=userRepository.findById(booking.getUser().getUserId());
		User user=optional1.orElseThrow(()-> new WanderLustException("BookingService.INVALID_USER"));

		if(destination.getAvailability() < booking.getNoOfPeople()) {
			throw new WanderLustException("BookingService.NO_AVAILABILITY");
		}
		else {
			destination.setAvailability(destination.getAvailability()-booking.getNoOfPeople());

		}

			
			bookingEntity.setUser(user);
			bookingEntity.setTimeOfBooking(booking.getTimeOfBooking());
			bookingEntity.setDestination(destination);
			bookingRepository.save(bookingEntity);
			
		
			return bookingEntity.getDestination().getDestinationName();			
				
	}
	public List<Booking> viewBookings(Integer userId) throws WanderLustException{
		List<Booking> bookingView=bookingRepository.findUserByUserId(userId);
			if(bookingView.isEmpty())
				throw new WanderLustException("BookingService.NO_BOOKINGS_FOUND");
		
		return bookingView;
		
	}
	
	
	
}