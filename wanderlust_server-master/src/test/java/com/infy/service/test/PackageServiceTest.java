package com.infy.service.test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.infy.dto.DestinationDTO;
import com.infy.dto.DetailsDTO;
import com.infy.dto.ItineraryDTO;
import com.infy.entity.Destination;
import com.infy.entity.Details;
import com.infy.entity.Itinerary;
import com.infy.repository.PackageRepository;
import com.infy.service.PackageServiceImpl;

@SpringBootTest
public class PackageServiceTest {
	
	@Mock
	PackageRepository packageRepository;
	
	@InjectMocks
	PackageServiceImpl packageServiceImpl;
	
	@Test
	public void invalidPackage() {
		List<Destination> destinations = new ArrayList<>();
		
		Mockito.when(packageRepository.findByContinent("India")).thenReturn(destinations);
		Exception exception = Assertions.assertThrows(Exception.class,
				() -> packageServiceImpl.findDestinations("India"));
		Assertions.assertEquals("PackageService.NO_DESTINATIONS_FOUND", exception.getMessage());
	}
	
	@Test
	public void getDestinationBySearchInvalid() {
		List<Destination> destinations = new ArrayList<>();
		
		Mockito.when(packageRepository.findByContinentOrDestinationName("India","Asia")).thenReturn(destinations);
		Exception exception = Assertions.assertThrows(Exception.class,
				() -> packageServiceImpl.findDestinations("India"));
		Assertions.assertEquals("PackageService.NO_DESTINATIONS_FOUND", exception.getMessage());
	}
	
	@Test
	public void getHotDealsInvalid() {
		List<Destination> destinations = new ArrayList<>();
		
		Mockito.when(packageRepository.findAll()).thenReturn(destinations);
		Exception exception = Assertions.assertThrows(Exception.class,
				() -> packageServiceImpl.getHotDeals());
		Assertions.assertEquals("PackageService.NO_HOT_DEALS", exception.getMessage());
	}
	
	@Test
	public void findDestinationValid() throws Exception {
		List<Destination> destinations = new ArrayList<>();
		List<DestinationDTO> destinationDTOs = new ArrayList<>();
		Destination dest=new Destination();
		dest.setDestinationId("D1001");
		dest.setContinent("Asia");
		dest.setChargePerPerson(5000);
		dest.setDiscount(1);
		dest.setAvailability(25);
		dest.setDestinationName("India");
		dest.setFlightCharge(1000);
		dest.setImageUrl("dd");
		dest.setNoOfNights(10);
		
		Details details=new Details();
		details.setDetailsId("DL101");
		
		Itinerary itinerary=new Itinerary();
		itinerary.setItineraryId("I1001");
		
		details.setItinerary(itinerary);
		dest.setDetails(details);
		
		destinations.add(dest);
		
		DestinationDTO destinationDTO = new DestinationDTO();
		destinationDTO.setDestinationId(dest.getDestinationId());
		
		DetailsDTO detailsDTO=new DetailsDTO();
		detailsDTO.setDetailsId("DL101");
		
		ItineraryDTO itineraryDTO = new ItineraryDTO();
		itineraryDTO.setItineraryId("I1001");
		
		detailsDTO.setItinerary(itineraryDTO);
		destinationDTO.setDetails(detailsDTO);
		destinationDTOs.add(destinationDTO);
		
		Mockito.when(packageRepository.findByContinent(dest.getContinent())).thenReturn(destinations);
		
		List<DestinationDTO> actual=packageServiceImpl.findDestinations("Asia");
		Assertions.assertNotEquals(destinationDTOs,actual);
	}
	
	@Test
	public void hotDealsValid() throws Exception {
		List<Destination> destinations = new ArrayList<>();
		List<DestinationDTO> destinationDTOs = new ArrayList<>();
		Destination dest=new Destination();
		dest.setDestinationId("D1001");
		dest.setContinent("Asia");
		dest.setChargePerPerson(5000);
		dest.setDiscount(1);
		dest.setAvailability(25);
		dest.setDestinationName("India");
		dest.setFlightCharge(1000);
		dest.setImageUrl("dd");
		dest.setNoOfNights(10);
		
		Details details=new Details();
		details.setDetailsId("DL101");
		
		Itinerary itinerary=new Itinerary();
		itinerary.setItineraryId("I1001");
		
		details.setItinerary(itinerary);
		dest.setDetails(details);
		
		destinations.add(dest);
		
		DestinationDTO destinationDTO = new DestinationDTO();
		destinationDTO.setDestinationId(dest.getDestinationId());
		
		DetailsDTO detailsDTO=new DetailsDTO();
		detailsDTO.setDetailsId("DL101");
		
		ItineraryDTO itineraryDTO = new ItineraryDTO();
		itineraryDTO.setItineraryId("I1001");
		
		detailsDTO.setItinerary(itineraryDTO);
		destinationDTO.setDetails(detailsDTO);
		destinationDTOs.add(destinationDTO);
		
		Mockito.when(packageRepository.findAll()).thenReturn(destinations);
		
		List<DestinationDTO> actual=packageServiceImpl.getHotDeals();
		Assertions.assertNotEquals(destinationDTOs,actual);
	}
	
	@Test
	public void findAllDestinationsValid() throws Exception {
		List<Destination> destinations = new ArrayList<>();
		List<DestinationDTO> destinationDTOs = new ArrayList<>();
		Destination dest=new Destination();
		dest.setDestinationId("D1001");
		dest.setContinent("Asia");
		dest.setChargePerPerson(5000);
		dest.setDiscount(1);
		dest.setAvailability(25);
		dest.setDestinationName("India");
		dest.setFlightCharge(1000);
		dest.setImageUrl("dd");
		dest.setNoOfNights(10);
		
		Details details=new Details();
		details.setDetailsId("DL101");
		
		Itinerary itinerary=new Itinerary();
		itinerary.setItineraryId("I1001");
		
		details.setItinerary(itinerary);
		dest.setDetails(details);
		
		destinations.add(dest);
		
		DestinationDTO destinationDTO = new DestinationDTO();
		destinationDTO.setDestinationId(dest.getDestinationId());
		
		DetailsDTO detailsDTO=new DetailsDTO();
		detailsDTO.setDetailsId("DL101");
		
		ItineraryDTO itineraryDTO = new ItineraryDTO();
		itineraryDTO.setItineraryId("I1001");
		
		detailsDTO.setItinerary(itineraryDTO);
		destinationDTO.setDetails(detailsDTO);
		destinationDTOs.add(destinationDTO);
		
		Mockito.when(packageRepository.findAll()).thenReturn(destinations);
		
		List<DestinationDTO> actual=packageServiceImpl.findAllDestinations();
		Assertions.assertNotEquals(destinationDTOs,actual);
	}
	
//	@Test
//	public void getDestinationBySearchValid() throws Exception {
//		List<Destination> destinations = new ArrayList<>();
//		List<DestinationDTO> destinationDTOs = new ArrayList<>();
//		Destination dest=new Destination();
//		dest.setDestinationId("D1001");
//		dest.setContinent("Asia");
//		dest.setChargePerPerson(5000);
//		dest.setDiscount(1);
//		dest.setAvailability(25);
//		dest.setDestinationName("India");
//		dest.setFlightCharge(1000);
//		dest.setImageUrl("dd");
//		dest.setNoOfNights(10);
//		
//		Details details=new Details();
//		details.setDetailsId("DL101");
//		
//		Itinerary itinerary=new Itinerary();
//		itinerary.setItineraryId("I1001");
//		
//		details.setItinerary(itinerary);
//		dest.setDetails(details);
//		
//		destinations.add(dest);
//		
//		DestinationDTO destinationDTO = new DestinationDTO();
//		destinationDTO.setDestinationId(dest.getDestinationId());
//		
//		DetailsDTO detailsDTO=new DetailsDTO();
//		detailsDTO.setDetailsId("DL101");
//		
//		ItineraryDTO itineraryDTO = new ItineraryDTO();
//		itineraryDTO.setItineraryId("I1001");
//		
//		detailsDTO.setItinerary(itineraryDTO);
//		destinationDTO.setDetails(detailsDTO);
//		destinationDTOs.add(destinationDTO);
//		
//		Mockito.when(packageRepository.findByContinentOrDestinationName("India","Asia")).thenReturn(destinations);
//		
//		List<DestinationDTO> actual=packageServiceImpl.getDestinationBySearch("Asia");
//		Assertions.assertNotEquals(destinationDTOs,actual);
//	}

}
