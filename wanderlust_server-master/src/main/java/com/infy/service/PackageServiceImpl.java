package com.infy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.infy.repository.PackageRepository;
import com.infy.dto.DestinationDTO;
import com.infy.dto.DetailsDTO;
import com.infy.dto.ItineraryDTO;
import com.infy.entity.Destination;
import com.infy.exception.WanderLustException;

//Component is a service component
@Service(value = "packagesService")

//Annotating the component to handle Transactions
@Transactional
public class PackageServiceImpl implements PackageService {
	
	@Autowired
	private PackageRepository packageRepository;
	
	@Override
	//Method to find destinations
	public List<DestinationDTO> findDestinations(String continent) throws Exception {
		List<Destination> destinations = packageRepository.findByContinent(continent);
		
		if(destinations.isEmpty()) {
			throw new Exception("PackageService.NO_DESTINATIONS_FOUND");
		}
		else {
			// returning destinations
			List<DestinationDTO> destinationDTOs=new ArrayList<>();
			destinations.forEach(
				des->{
				DestinationDTO destinationDTO=new DestinationDTO();
				destinationDTO.setAvailability(des.getAvailability());
				destinationDTO.setChargePerPerson(des.getChargePerPerson());
				destinationDTO.setContinent(des.getContinent());
				destinationDTO.setDestinationId(des.getDestinationId());
				destinationDTO.setDestinationName(des.getDestinationName());
				destinationDTO.setDiscount(des.getDiscount());
				destinationDTO.setFlightCharge(des.getFlightCharge());
				destinationDTO.setImageUrl(des.getImageUrl());
				destinationDTO.setNoOfNights(des.getNoOfNights());
				
				DetailsDTO detailsDTO=new DetailsDTO();
				detailsDTO.setAbout(des.getDetails().getAbout());
				detailsDTO.setDetailsId(des.getDetails().getDetailsId());
				detailsDTO.setHighlights(des.getDetails().getHighlights());
				detailsDTO.setPace(des.getDetails().getPace());
				detailsDTO.setPackageInclusion(des.getDetails().getPackageInclusion());
				destinationDTO.setDetails(detailsDTO);
				
				ItineraryDTO itineraryDTO = new ItineraryDTO();
				itineraryDTO.setFirstDay(des.getDetails().getItinerary().getFirstDay());
				itineraryDTO.setItineraryId(des.getDetails().getItinerary().getItineraryId());
				itineraryDTO.setLastDay(des.getDetails().getItinerary().getLastDay());
				itineraryDTO.setRestOfDays(des.getDetails().getItinerary().getRestOfDays());
				detailsDTO.setItinerary(itineraryDTO);
				
				destinationDTOs.add(destinationDTO);
		});
			return destinationDTOs;
		}
	}
	
	@Override
	public List<DestinationDTO> getHotDeals() throws Exception {
		
		Iterable<Destination> allDestinations = packageRepository.findAll();
		//List of Destination Objects
		List<DestinationDTO> hotDeals  = new ArrayList<>();														

		allDestinations.forEach(item->{
			if(item.getDiscount()>0.0 ) {
				
				DestinationDTO dto=new DestinationDTO();
				
				dto.setDestinationId(item.getDestinationId());
				dto.setContinent(item.getContinent());
				dto.setDestinationName(item.getDestinationName());
				dto.setImageUrl(item.getImageUrl());
				dto.setNoOfNights(item.getNoOfNights());
				dto.setFlightCharge(item.getFlightCharge());
				dto.setChargePerPerson(item.getChargePerPerson());
				dto.setDiscount(item.getDiscount());
				dto.setAvailability(item.getAvailability());
				
				DetailsDTO details = new DetailsDTO();
				details.setDetailsId(item.getDetails().getDetailsId());
				details.setAbout(item.getDetails().getAbout());
				details.setPackageInclusion(item.getDetails().getPackageInclusion());
				details.setHighlights(item.getDetails().getHighlights());
				details.setPace(item.getDetails().getPace());
				
				ItineraryDTO itinerary = new ItineraryDTO();
				itinerary.setItineraryId(item.getDetails().getItinerary().getItineraryId());
				itinerary.setFirstDay(item.getDetails().getItinerary().getFirstDay());
				itinerary.setRestOfDays(item.getDetails().getItinerary().getRestOfDays());
				itinerary.setLastDay(item.getDetails().getItinerary().getLastDay());
				
				details.setItinerary(itinerary);
				dto.setDetails(details);
				
				//Adding the Object to the Destination List
				hotDeals.add(dto);					
				
			}
		});
		
		if (hotDeals.isEmpty()) {
			//Raising the Exception if there is No discounted deals.
			throw new Exception("PackageService.NO_HOT_DEALS");			
		} else {
			//Returning the Destination deals.
			return hotDeals;												
		}
	}
	
	public List<DestinationDTO> findAllDestinations() throws Exception {
		Iterable<Destination> destinations = packageRepository.findAll();
		
		List<DestinationDTO> allDestinations=new ArrayList<>();
		
		destinations.forEach(item->{
				
				DestinationDTO dto=new DestinationDTO();
				
				dto.setDestinationId(item.getDestinationId());
				dto.setContinent(item.getContinent());
				dto.setDestinationName(item.getDestinationName());
				dto.setImageUrl(item.getImageUrl());
				dto.setNoOfNights(item.getNoOfNights());
				dto.setFlightCharge(item.getFlightCharge());
				dto.setChargePerPerson(item.getChargePerPerson());
				dto.setDiscount(item.getDiscount());
				dto.setAvailability(item.getAvailability());
				
				DetailsDTO details = new DetailsDTO();
				details.setDetailsId(item.getDetails().getDetailsId());
				details.setAbout(item.getDetails().getAbout());
				details.setPackageInclusion(item.getDetails().getPackageInclusion());
				details.setHighlights(item.getDetails().getHighlights());
				details.setPace(item.getDetails().getPace());
				
				ItineraryDTO itinerary = new ItineraryDTO();
				itinerary.setItineraryId(item.getDetails().getItinerary().getItineraryId());
				itinerary.setFirstDay(item.getDetails().getItinerary().getFirstDay());
				itinerary.setRestOfDays(item.getDetails().getItinerary().getRestOfDays());
				itinerary.setLastDay(item.getDetails().getItinerary().getLastDay());
				
				details.setItinerary(itinerary);
				dto.setDetails(details);
				//Adding the Object to the Destination List
				allDestinations.add(dto);						
		});
		if (allDestinations.isEmpty()) {
			//Raising the Exception if there is No discounted deals.
			throw new Exception("PackageService.NO_DESTINATION_AVAILABLE");			
		} else {
			//Returning the Destinations.
			return allDestinations;												
		}
	}
	
	public List<DestinationDTO> getDestinationBySearch(String continentOrPackage) throws Exception {
		String packageName=continentOrPackage;
		String continent=continentOrPackage;
		List<Destination> destinations = packageRepository.findByContinentOrDestinationName(packageName,continent);
		
		List<DestinationDTO> allDestinations=new ArrayList<>();
		
		if(destinations.isEmpty()) {
			throw new WanderLustException("Package.NO_PACKAGES_FOUND");
		}
		else {
		destinations.forEach(item->{
				
				DestinationDTO dto=new DestinationDTO();
				
				dto.setDestinationId(item.getDestinationId());
				dto.setContinent(item.getContinent());
				dto.setDestinationName(item.getDestinationName());
				dto.setImageUrl(item.getImageUrl());
				dto.setNoOfNights(item.getNoOfNights());
				dto.setFlightCharge(item.getFlightCharge());
				dto.setChargePerPerson(item.getChargePerPerson());
				dto.setDiscount(item.getDiscount());
				dto.setAvailability(item.getAvailability());
				
				DetailsDTO details = new DetailsDTO();
				details.setDetailsId(item.getDetails().getDetailsId());
				details.setAbout(item.getDetails().getAbout());
				details.setPackageInclusion(item.getDetails().getPackageInclusion());
				details.setHighlights(item.getDetails().getHighlights());
				details.setPace(item.getDetails().getPace());
				
				ItineraryDTO itinerary = new ItineraryDTO();
				itinerary.setItineraryId(item.getDetails().getItinerary().getItineraryId());
				itinerary.setFirstDay(item.getDetails().getItinerary().getFirstDay());
				itinerary.setRestOfDays(item.getDetails().getItinerary().getRestOfDays());
				itinerary.setLastDay(item.getDetails().getItinerary().getLastDay());
				
				details.setItinerary(itinerary);
				dto.setDetails(details);
				//Adding the Object to the Destination List
				allDestinations.add(dto);						
		});
			//Returning the Destinations.
			return allDestinations;												
	  }
	}
}
