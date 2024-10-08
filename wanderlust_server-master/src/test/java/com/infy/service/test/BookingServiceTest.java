package com.infy.service.test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.infy.dto.BookingDTO;
import com.infy.dto.DestinationDTO;
import com.infy.dto.DetailsDTO;
import com.infy.dto.ItineraryDTO;
import com.infy.dto.UserDTO;
import com.infy.entity.Booking;
import com.infy.entity.Destination;
import com.infy.entity.Details;
import com.infy.entity.Itinerary;
import com.infy.entity.User;
import com.infy.exception.WanderLustException;
import com.infy.repository.BookingRepository;
import com.infy.repository.PackageRepository;
import com.infy.repository.UserRepository;
import com.infy.service.BookingServiceImpl;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class BookingServiceTest {
	
	@Mock
	PackageRepository packageRepository;
	
	@Mock
	BookingRepository bookingRepository;

	@Mock
	UserRepository userRepository;

	
	@InjectMocks
	BookingServiceImpl bookingServiceImpl;
	
	@Test
	public void bookPackageInvalidDestination() {


		BookingDTO booking=new BookingDTO();
		booking.setCheckIn(LocalDate.of(2021,5,4));
		booking.setCheckOut(LocalDate.of(2021,5,11));
		booking.setNoOfPeople(3);
		booking.setTimeOfBooking(new Timestamp(System.currentTimeMillis()));
		booking.setTotalCost(2345.0f);
		
		DestinationDTO d=new DestinationDTO();
		d.setDestinationId("D1001");
		
		DetailsDTO det=new DetailsDTO();
		det.setDetailsId("DL103");
		
		ItineraryDTO i=new ItineraryDTO();
		i.setItineraryId("I1003");
		
		det.setItinerary(i);
		d.setDetails(det);
		
		UserDTO user=new UserDTO();
		user.setUserId(101);
		
		booking.setDestination(d);
		booking.setUser(user);
		
		Mockito.when(packageRepository.findById("D1003")).thenReturn(Optional.empty());
		
		Exception exception = Assertions.assertThrows(Exception.class,
				() -> bookingServiceImpl.bookPackage(booking));
		Assertions.assertEquals("PackageService.DESTINATION_NOT_FOUND", exception.getMessage());
	}
	
	@Test
	public void bookPackageValid() throws Exception{


		BookingDTO booking=new BookingDTO();
		booking.setCheckIn(LocalDate.of(2021,5,4));
		booking.setCheckOut(LocalDate.of(2021,5,11));
		booking.setNoOfPeople(3);
		booking.setTimeOfBooking(new Timestamp(System.currentTimeMillis()));
		booking.setTotalCost(2345.0f);
		
		DestinationDTO d=new DestinationDTO();
		d.setDestinationId("D1001");
		
		d.setAvailability(10);
		d.setChargePerPerson(300);
		d.setContinent("asia");
		d.setDestinationName("china");
		d.setDiscount(350.0f);
		d.setFlightCharge(500f);
		d.setImageUrl("img.jpg");
		d.setNoOfNights(10);
		
		
		DetailsDTO det=new DetailsDTO();
		det.setDetailsId("DL103");
		
		ItineraryDTO i=new ItineraryDTO();
		i.setItineraryId("I1003");
		
		det.setItinerary(i);
		d.setDetails(det);
		
		Destination eee=new Destination();
		eee.setDestinationId("D1001");
		
		eee.setAvailability(10);
		eee.setChargePerPerson(300);
		eee.setContinent("asia");
		eee.setDestinationName("china");
		eee.setDiscount(350.0f);
		eee.setFlightCharge(500f);
		eee.setImageUrl("img.jpg");
		eee.setNoOfNights(10);
		
		Details en=new Details();
		en.setDetailsId("DL103");
		
		Itinerary it=new Itinerary();
		it.setItineraryId("I1003");
		
		en.setItinerary(it);
		eee.setDetails(en);
		

		UserDTO user=new UserDTO();
		user.setUserId(101);
		user.setContactNumber("7381788563");
		user.setEmailId("user@gmail.com");
		user.setUserName("user1");
		user.setPassword("userpswd");
		
		User userEnt=new User();
		userEnt.setUserId(101);		
		userEnt.setContactNumber("7381788563");
		userEnt.setEmailId("user@gmail.com");
		userEnt.setUserName("user1");
		userEnt.setPassword("userpswd");
		
		booking.setDestination(d);
		booking.setUser(user);
		
		Mockito.when(packageRepository.findById("D1001")).thenReturn(Optional.of(eee));
		Mockito.when(userRepository.findById(101)).thenReturn(Optional.of(userEnt));
		
		String actual=bookingServiceImpl.bookPackage(booking);
		Assertions.assertEquals("china",actual);
	}
	
	
	@Test
	public void bookPackageSeatsUnavailable() {


		BookingDTO booking=new BookingDTO();
		booking.setCheckIn(LocalDate.of(2021,5,4));
		booking.setCheckOut(LocalDate.of(2021,5,11));
		booking.setNoOfPeople(5);
		booking.setTimeOfBooking(new Timestamp(System.currentTimeMillis()));
		booking.setTotalCost(2345.0f);
		
		DestinationDTO d=new DestinationDTO();
		d.setDestinationId("D1003");
		
		
		DetailsDTO det=new DetailsDTO();
		det.setDetailsId("DL103");
		
		ItineraryDTO i=new ItineraryDTO();
		i.setItineraryId("I1003");
		
		det.setItinerary(i);
		d.setDetails(det);
		
		UserDTO user=new UserDTO();
		user.setUserId(101);
		
		User en=new User();
		en.setUserId(101);
		
		booking.setDestination(d);
		booking.setUser(user);
		
		Destination dEntity=new Destination();
		dEntity.setDestinationId("D1003");
		dEntity.setAvailability(3);
		dEntity.setChargePerPerson(345f);
		dEntity.setContinent("asia");
		dEntity.setDestinationName("china");
		dEntity.setDiscount(34f);
		dEntity.setFlightCharge(500f);
		dEntity.setNoOfNights(5);
		dEntity.setImageUrl("image.jpg");
		
		Details dt= new Details();
		dt.setDetailsId("DL103");
		
		Itinerary iti=new Itinerary();
		iti.setItineraryId("I1223");
		dt.setItinerary(iti);
		
		dEntity.setDetails(dt);

		
		Mockito.when(packageRepository.findById("D1003")).thenReturn(Optional.of(dEntity));
		Mockito.when(userRepository.findById(101)).thenReturn(Optional.of(en));
		
		Exception exception = Assertions.assertThrows(Exception.class,
				() -> bookingServiceImpl.bookPackage(booking));
		Assertions.assertEquals("BookingService.NO_AVAILABILITY", exception.getMessage());
	}
	
	
	@Test
	public void viewBookingInvalid() throws WanderLustException{
		List<Booking> booking=new ArrayList<>();
		
		Mockito.when(bookingRepository.findUserByUserId(Mockito.anyInt())).thenReturn(booking);
		
		Exception exception = Assertions.assertThrows(Exception.class,
				() -> bookingServiceImpl.viewBookings(1010));
		Assertions.assertEquals("BookingService.NO_BOOKINGS_FOUND", exception.getMessage());
		
	}
	
	
	
	

}