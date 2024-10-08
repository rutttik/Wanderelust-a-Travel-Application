//package com.infy.repository.test;
//
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//
//import com.infy.entity.Destination;
//import com.infy.repository.PackageRepository;
//
//@DataJpaTest
//public class PackageRepositoryTest {
//	
//	@Autowired
//	PackageRepository packageRepository;
//	
//	private Destination destination;
//	
//	@BeforeEach
//	public void setUp() {
//		
//		destination=new Destination();
//		destination.setDestinationId("101");
//		destination.setContinent("Asia");
//		
//	}
//	
//	@Test
//	public void verifyValidDestination() throws Exception{
//		Assertions.assertNotNull(packageRepository.findByContinent("Asia"));
//	}
//
//	@Test
//	public void verifyInvalidDestination() throws Exception{
//		Assertions.assertNotNull(packageRepository.findByContinent("xyz").isEmpty());
//	}
//}
