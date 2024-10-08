package com.infy.api;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.core.env.Environment;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.CrossOrigin;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
	import org.springframework.web.server.ResponseStatusException;

import com.infy.dto.DestinationDTO;
	import com.infy.service.PackageService;

	@CrossOrigin

	//Making the class as a Rest Controller
	@RestController	

	//Mapping all the base URL of the API to /Packages API
	@RequestMapping("PackagesAPI")						
	public class PackagesAPI {
		
		//Autowiring the packages Service
		@Autowired											
		private PackageService packageService;
		
		//Autowiring the Environment Object for accessing properties
		@Autowired											
		private Environment environment;
		
		@GetMapping(value = "/{continent}") 			 
		public ResponseEntity<List<DestinationDTO>> getDestinationBySearch(@PathVariable String continent) throws Exception {
			try {
				List<DestinationDTO> allDestinations = packageService.getDestinationBySearch(continent);
				
				return new ResponseEntity<List<DestinationDTO>>(allDestinations,HttpStatus.OK); 
			} catch(Exception exception) {
				
				 //If Destinations are not found sending the appropriate Error Message as response.
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(exception.getMessage()));
			}
		}
		
		//Using Get Mapping and mapping the URL to /discountedDeals to get he top 3 discounted deals
				@GetMapping(value = "/hotdeals") 			
				public ResponseEntity<List<DestinationDTO>> getDiscountedDeals() throws Exception {
					try {
						List<DestinationDTO> hotDeals = packageService.getHotDeals();
						
						//If Deals are found sending the deals as Response
						return new ResponseEntity<List<DestinationDTO>>(hotDeals,HttpStatus.OK); 
					} catch (Exception exception) {
						
						 //If deals are not found sending the appropriate Error Message as response.
						throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(exception.getMessage()));
					}
				}
				
				
				@GetMapping(value = "/allPackages") 			 
				public ResponseEntity<List<DestinationDTO>> getAllDestinations() throws Exception {
					try {
						List<DestinationDTO> allDestinations = packageService.findAllDestinations();
						
						return new ResponseEntity<List<DestinationDTO>>(allDestinations,HttpStatus.OK); 
					} catch(Exception exception) {
						
						 //If Destinations are not found sending the appropriate Error Message as response.
						throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(exception.getMessage()));
					}
				}
	}


