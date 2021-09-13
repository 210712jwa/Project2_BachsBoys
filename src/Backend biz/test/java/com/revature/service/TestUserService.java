package com.revature.service;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDAO;
import com.revature.dto.AddUserDTO;
import com.revature.dto.MessageDTO;
import com.revature.exception.BadParameterException;
import com.revature.exception.DatabaseException;
import com.revature.model.User;


public class TestUserService {
	
	private UserService userService;
	private UserDAO userDao;

	@BeforeEach
	public void setUp() throws Exception {
		this.userDao = mock(UserDAO.class); //using mockito to creat fake user doa object
		
		this.userService = new UserService(userDao); //injecting mocked object into userservice object
	}


	//test case for "happy path"
	@Test
	public void test_getAllUsers_positive() throws DatabaseException, SQLException {
		//becuase shipdoa object is mocked, we need to specify what we want the mocked clientdao to return
		//whenever we invoke the clientdoa 
		List<User> mockReturnValues = new ArrayList<>();
		mockReturnValues.add(new User("bivy", "1234bi", "bill", "ivy"));
		mockReturnValues.add(new User("jivy", "1234ji", "jill", "ivy"));
		mockReturnValues.add(new User("tivy", "1234ti", "tina", "ivy"));
		
		when(this.userService.getAllUsers()).thenReturn(mockReturnValues);
		
		List<User> actual = userService.getAllUsers();
		
		//this is whats expected for clients list
		List<User> expected = new ArrayList<>();
		expected.add(new User("bivy", "1234bi", "bill", "ivy"));
		expected.add(new User("jivy", "1234ji", "jill", "ivy"));
		expected.add(new User("tivy", "1234ti", "tina", "ivy"));
		
		assertEquals(expected.size(), actual.size());
		
	}
	
//	@Test
//	public void test_getAllClients_negative() throws DatabaseException, SQLException {
//		//becuase shipdoa object is mocked, we need to specify what we want the mocked clientdao to return
//		//whenever we invoke the clientdoa 
//		
//		when(this.userService.getAllUsers()).thenThrow(SQLException.class);
//		
//		userService.getAllUsers();
//
//		
//	}
	
	
	@Test
	public void test_addUser_positive() throws SQLException, BadParameterException {	
		
		AddUserDTO dto = new AddUserDTO();
		dto.setFirstName("bill");
		dto.setLastName("ivy");
		dto.setUsername("bivy");
		dto.setPassword("1234bi");
		
		User user = userService.addUser(dto);

		
		when(userDao.addUser(eq(dto))).thenReturn(new User("bivy", "1234bi", "bill", "ivy"));
		
		User actual = userService.addUser(dto);
		
		assertEquals("bivy", actual.getUsername());
	}
	
	@Test
	public void test_addUser_blankFirstname() throws SQLException, BadParameterException {
		
		AddUserDTO dto = new AddUserDTO();
		dto.setFirstName("");
		dto.setLastName("ivy");
		dto.setUsername("bivy");
		dto.setPassword("1234bi");
		
		try {
			userService.addUser(dto);
			
			fail();
		} catch (BadParameterException e) {
			assertEquals("First Name field is blank!", e.getMessage());
		}
	

	}
	
	@Test
	public void test_addUser_blankLastname() throws SQLException, BadParameterException {
		
		AddUserDTO dto = new AddUserDTO();
		dto.setFirstName("bill");
		dto.setLastName("");
		dto.setUsername("bivy");
		dto.setPassword("1234bi");
		
		try {
			userService.addUser(dto);
			
			fail();
		} catch (BadParameterException e) {
			assertEquals("Last Name field is blank!", e.getMessage());
		}
	

	}
	
	@Test
	public void test_addUser_blankPassword() throws SQLException, BadParameterException {
		
		AddUserDTO dto = new AddUserDTO();
		dto.setFirstName("bill");
		dto.setLastName("ivy");
		dto.setUsername("bivy");
		dto.setPassword("");
		
		try {
			userService.addUser(dto);
			
			fail();
		} catch (BadParameterException e) {
			assertEquals("Password field is blank!", e.getMessage());
		}
	

	}
	
	@Test
	public void test_addUser_blankUsername() throws SQLException, BadParameterException {
		
		AddUserDTO dto = new AddUserDTO();
		dto.setFirstName("bill");
		dto.setLastName("ivy");
		dto.setUsername("");
		dto.setPassword("1234bi");
		
		try {
			userService.addUser(dto);
			
			fail();
		} catch (BadParameterException e) {
			assertEquals("Username field is blank!", e.getMessage());
		}
	

	}
	
	
	@Test
	public void test_addUser_spacesFirstname() throws SQLException, BadParameterException {
		
		AddUserDTO dto = new AddUserDTO();
		dto.setFirstName("     ");
		dto.setLastName("ivy");
		dto.setUsername("bivy");
		dto.setPassword("1234bi");
		
		try {
			userService.addUser(dto);
			
			fail();
		} catch (BadParameterException e) {
			assertEquals("First Name field is blank!", e.getMessage());
		}
	

	}
	
	@Test
	public void test_addUser_spacesLastname() throws SQLException, BadParameterException {
		
		AddUserDTO dto = new AddUserDTO();
		dto.setFirstName("bill");
		dto.setLastName("      ");
		dto.setUsername("bivy");
		dto.setPassword("1234bi");
		
		try {
			userService.addUser(dto);
			
			fail();
		} catch (BadParameterException e) {
			assertEquals("Last Name field is blank!", e.getMessage());
		}
	

	}
	
	@Test
	public void test_addUser_spacesPassword() throws SQLException, BadParameterException {
		
		AddUserDTO dto = new AddUserDTO();
		dto.setFirstName("bill");
		dto.setLastName("ivy");
		dto.setUsername("bivy");
		dto.setPassword("      ");
		
		try {
			userService.addUser(dto);
			
			fail();
		} catch (BadParameterException e) {
			assertEquals("Password field is blank!", e.getMessage());
		}
	

	}
	
	@Test
	public void test_addUser_spacesUsername() throws SQLException, BadParameterException {
		
		AddUserDTO dto = new AddUserDTO();
		dto.setFirstName("bill");
		dto.setLastName("ivy");
		dto.setUsername("      ");
		dto.setPassword("1234bi");
		
		try {
			userService.addUser(dto);
			
			fail();
		} catch (BadParameterException e) {
			assertEquals("Username field is blank!", e.getMessage());
		}
	

	}
	
	
	@Test
	public void test_getUserByUsername_positive() throws DatabaseException, SQLException {
		
		when(this.userService.getUserByUsername("bivy")).thenReturn(new User("bivy", "1234bi", "bill", "ivy"));
		
		User actual = userService.getUserByUsername("bivy");
		
		User expected = new User("bivy", "1234bi", "bill", "ivy");

		
		assertEquals(expected.getUsername(), actual.getUsername());
		
	}
	
	

}
