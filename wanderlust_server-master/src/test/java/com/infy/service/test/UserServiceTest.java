package com.infy.service.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.dto.UserDTO;
import com.infy.entity.User;
import com.infy.exception.WanderLustException;
import com.infy.repository.UserRepository;
import com.infy.service.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Test
	public void invalidLoginInvalidUser() throws Exception {
		User user = new User();

		String contactNo = "1234567890";
		String password = "abcd";

		user.setPassword("xyz");

		Mockito.when(userRepository.findByContactNumber(contactNo)).thenReturn(user);
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> userServiceImpl.authenticateUser(contactNo, password));
		Assertions.assertEquals("UserService.INVALID_CREDENTIALS", exception.getMessage());

	}

	@Test
	public void invalidLoginNullPassword() throws Exception {
		User user = new User();

		String contactNo = "1234567890";
		String password = "abcd";

		user.setPassword(null);

		Mockito.when(userRepository.findByContactNumber(contactNo)).thenReturn(user);
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> userServiceImpl.authenticateUser(contactNo, password));
		Assertions.assertEquals("UserService.INVALID_CREDENTIALS", exception.getMessage());

	}
	
	@Test
	public void contactNumberAlreadyExists() throws Exception{
		User user=new User();
		user.setContactNumber("8882039476");
		
		UserDTO userDTO=new UserDTO();
		userDTO.setContactNumber("8882039476");
		userDTO.setEmailId("tom@gmail.com");
		userDTO.setPassword("Tom@123");
		userDTO.setUserName("Tom Jerry");
		Mockito.when(userRepository.findByContactNumber(user.getContactNumber())).thenReturn(user);
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> userServiceImpl.registerUser(userDTO));
		Assertions.assertEquals("UserService.CONTACT_NUMBER_ALREADY_EXISTS", exception.getMessage());
	}
	
	@Test
	public void invalidRegistrationNullPassword() throws Exception{
		User user=new User();
		user.setContactNumber("8882039476");
		
		UserDTO userDTO=new UserDTO();
		userDTO.setContactNumber("8882039476");
		userDTO.setEmailId("tom@gmail.com");
		userDTO.setPassword("om123");
		userDTO.setUserName("Tom Jerry");
		Mockito.when(userRepository.save(user)).thenReturn(user);
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> userServiceImpl.registerUser(userDTO));
		Assertions.assertEquals("UserValidator.INVALID_PASSWORD_FORMAT", exception.getMessage());
	}
	
	@Test
	public void validUserRegistration() throws Exception{
		User user=new User();
		user.setContactNumber("8882039476");
		
		UserDTO userDTO=new UserDTO();
		userDTO.setContactNumber("8882039476");
		userDTO.setEmailId("tom@gmail.com");
		userDTO.setPassword("Tom@123");
		userDTO.setUserName("Tom Jerry");
		Mockito.when(userRepository.save(user)).thenReturn(user);
		String actual=userServiceImpl.registerUser(userDTO);
		Assertions.assertEquals("Tom Jerry", actual);
	}

	@Test
	public void invalidRegistrationNullEmail() throws Exception{
		User user=new User();
		user.setContactNumber("8882039476");
		
		UserDTO userDTO=new UserDTO();
		userDTO.setContactNumber("8882039476");
		userDTO.setEmailId(null);
		userDTO.setPassword("Tom@123");
		userDTO.setUserName("Tom Jerry");
		Mockito.when(userRepository.save(user)).thenReturn(user);
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> userServiceImpl.registerUser(userDTO));
		Assertions.assertEquals("UserValidator.INVALID_EMAIL_ID_FORMAT", exception.getMessage());
	}
	
	@Test
	public void invalidRegistrationNullUserName() throws Exception{
		User user=new User();
		user.setContactNumber("8882039476");
		
		UserDTO userDTO=new UserDTO();
		userDTO.setContactNumber("8882039476");
		userDTO.setEmailId("tom@gmail.com");
		userDTO.setPassword("Tom@123");
		userDTO.setUserName(null);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> userServiceImpl.registerUser(userDTO));
		Assertions.assertEquals("UserValidator.INVALID_USERNAME_FORMAT", exception.getMessage());
	}
	
	@Test
	public void invalidRegistrationNullContactNo() throws Exception{
		User user=new User();
		user.setContactNumber("8882039476");
		
		UserDTO userDTO=new UserDTO();
		userDTO.setContactNumber(null);
		userDTO.setEmailId("tom@gmail.com");
		userDTO.setPassword("Tom@123");
		userDTO.setUserName("Tom Jerry");
		Mockito.when(userRepository.save(user)).thenReturn(user);
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> userServiceImpl.registerUser(userDTO));
		Assertions.assertEquals("UserValidator.INVALID_CONTACT_NUMBER_FORMAT", exception.getMessage());
	}
	
	
//	@Test
//	public void invalidLoginInvalidPassword() throws Exception {
//		ee.expect(Exception.class);
//		ee.expectMessage("UserService.INVALID_CREDENTIALS");
//		UserDTO users = new UserDTO();
//		users.setPassword("abc");
//		when(userRepository.getUserByContactNumber(anyString())).thenReturn(users);
//		userServiceImpl.authenticateUser("8889765465", "Scott@123");
//	}
//
//	@Test
//	public void validLogin() throws Exception {
//		UserDTO expected = new UserDTO();
//		expected.setPassword("3284cbd43ac6fc733d7c3d2176e7a52bbaeba81471702ec332a0a65689cf16e3");
//		expected.setContactNumber("8884967823");
//		when(userRepository.getUserByContactNumber(anyString())).thenReturn(expected);
//		UserDTO actual = userServiceImpl.authenticateUser("8889765465", "Scott@123");
//		Assertions.assertEquals(expected.getContactNumber(), actual.getContactNumber());
//	}
//
}
