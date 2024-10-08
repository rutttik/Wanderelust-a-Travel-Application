package com.infy.service;

import java.util.List;

import com.infy.dto.DestinationDTO;


public interface PackageService {

	public List<DestinationDTO> findDestinations(String continent) throws Exception;
	public List<DestinationDTO> getHotDeals() throws Exception ;
	public List<DestinationDTO> findAllDestinations() throws Exception ;
	List<DestinationDTO> getDestinationBySearch(String continentOrPackage) throws Exception;
}
