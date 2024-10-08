package com.infy.validator.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

//import com.infy.dto.BookingDTO;
//import com.infy.dto.DestinationDTO;
//import com.infy.dto.DetailsDTO;
//import com.infy.dto.ItineraryDTO;
//import com.infy.dto.UserDTO;
//import com.infy.entity.Destination;
//import com.infy.entity.Details;
//import com.infy.entity.Itinerary;
//import com.infy.entity.User;
import com.infy.validator.UserValidator;

public class UserValidatorTest {

	@Rule
	public ExpectedException ee = ExpectedException.none();

	@Test
	public void validatePasswordValidTest() {
		Boolean result = UserValidator.validatePassword("Scott@123");
		Assert.assertTrue(result);
	}

	@Test
	public void validatePasswordInvalidTest() {
		Boolean result = UserValidator.validatePassword("scott@123");
		Assert.assertFalse(result);
	}

	@Test
	public void validatePasswordInValidTest1() throws Exception {
		Boolean result = UserValidator.validatePassword("A123456");
		Assert.assertFalse(result);
	}

	@Test
	public void validatePasswordInValidTest2() throws Exception {
		Boolean result = UserValidator.validatePassword("a123456");
		Assert.assertFalse(result);
	}

	@Test
	public void validatePasswordInValidTest3() throws Exception {
		Boolean result = UserValidator.validatePassword("null");
		Assert.assertFalse(result);
	}

	@Test
	public void validatePasswordInValidTest4() throws Exception {
		Boolean result = UserValidator.validatePassword("45658963214785");
		Assert.assertFalse(result);
	}

	@Test
	public void validateContactNumberValidTest() {
		Boolean result = UserValidator.validateContactNumber("8420305506");
		Assert.assertTrue(result);
	}

	@Test
	public void validateContactNumberInvalidTest() {
		Boolean result = UserValidator.validateContactNumber("5678901232");
		Assert.assertFalse(result);
	}

	@Test
	public void validateContactNumberInvalidTest1() {
		Boolean result = UserValidator.validateContactNumber("98678901");
		Assert.assertFalse(result);
	}

	@Test
	public void validateContactNumberInvalidTest2() {
		Boolean result = UserValidator.validateContactNumber(null);
		Assert.assertFalse(result);
	}

	@Test
	public void validateContactNumberInvalidTest3() {
		Boolean result = UserValidator.validateContactNumber("56789ab232");
		Assert.assertFalse(result);
	}

	@Test
	public void validateUserNameValidTest() {
		Boolean result = UserValidator.validateUserName("Brett Lee");
		Assert.assertTrue(result);
	}

	@Test
	public void validateUserNameInvalidTest() {
		Boolean result = UserValidator.validateUserName(" Brett Lee");
		Assert.assertFalse(result);
	}

	@Test
	public void validateUserNameInvalidTest1() {
		Boolean result = UserValidator.validateUserName(null);
		Assert.assertFalse(result);
	}

	@Test
	public void validateUserNameInvalidTest2() {
		Boolean result = UserValidator.validateUserName("A123ndy");
		Assert.assertFalse(result);
	}

	@Test
	public void validateUserNameInvalidTest3() {
		Boolean result = UserValidator.validateUserName("@ndy");
		Assert.assertFalse(result);
	}

	@Test
	public void validateEmailIdValidTest() throws Exception {
		Boolean result = UserValidator.validateEmailId("Jack@infosys.com");
		Assert.assertTrue(result);
	}

	@Test
	public void validateEmailIdInvalidTest() throws Exception {
		Boolean result = UserValidator.validateEmailId("Tom12@infosys1.c2om");
		Assert.assertFalse(result);
	}

	@Test
	public void validateUserLoginInvalidContactNumber() throws Exception {
		ee.expect(Exception.class);
		ee.expectMessage("UserValidator.INVALID_CONTACT_NUMBER_FORMAT");
		UserValidator.validateUserForLogin("98678901", "Jack@123");
	}

	@Test
	public void validateUserLoginInvalidPassword() throws Exception {
		ee.expect(Exception.class);
		ee.expectMessage("UserValidator.INVALID_PASSWORD_FORMAT");
		UserValidator.validateUserForLogin("8420305506", "jack123");
	}
	
	
	
	
}
