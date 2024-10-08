//package com.infy.repository.test;
//
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.infy.entity.Booking;
//import com.infy.entity.Destination;
//import com.infy.entity.Details;
//import com.infy.entity.Itinerary;
//import com.infy.entity.User;
//import com.infy.repository.BookingRepository;
//import com.infy.repository.PackageRepository;
//
//@DataJpaTest
//@SpringBootTest
//@RunWith(SpringRunner.class)
//
//public class BookingRepositoryTest {
//	
//	
//	
//	@Autowired
//	BookingRepository bookingRepository;
//	
//	private Booking booking;
//	
////	@BeforeEach
////	public void setUp()
////	{
////		booking=new Booking();
////		booking.setBookingId(101);
////		
////		User user=new User();
////		user.setUserId(101);
////		booking.setUser(user);
////		
////		Destination destination=new Destination();
////		destination.setDestinationId("101");
////		
////		Details details=new Details();
////		details.setDetailsId("101");
////		
////		Itinerary itinerary=new Itinerary();
////		itinerary.setItineraryId("101");
////		
////		details.setItinerary(itinerary);
////		destination.setDetails(details);
////		booking.setDestination(destination);
////	}Test cases
////	
//	@Test
//	public void verifyUserByUserIdValidTest(){
//		User user=new User();
//		user.setUserId(101);
//		
//		Assertions.assertNotNull(bookingRepository.findUserByUserId(101).isEmpty());
//	}
//	
//	@Test
//	public void verifyUserByUserIdInValidTest() throws Exception{
//		Assertions.assertNotNull(bookingRepository.findUserByUserId(10).isEmpty());
//	}
//	
//	@Test
//	public void addBookingValid() throws Exception{
//		Assertions.assertNotNull(bookingRepository.save(booking));
//	}
//	
//
//}
