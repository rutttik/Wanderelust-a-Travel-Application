package com.infy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.infy.entity.Destination;

public interface PackageRepository extends CrudRepository<Destination, String> {
	public List<Destination> findByContinent(String continent);
	@Query("select d from Destination d where d.destinationName like %:destinationName% or d.continent like %:continent%")
	List<Destination> findByContinentOrDestinationName(@Param("destinationName") String destinationName,@Param("continent") String continent);
}
