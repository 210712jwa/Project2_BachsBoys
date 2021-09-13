package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

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
import com.revature.dao.LoginDAO;
import com.revature.dao.UserDAO;
import com.revature.dto.AddUserDTO;
import com.revature.dto.LoginDTO;
import com.revature.dto.MessageDTO;
import com.revature.exception.BadParameterException;
import com.revature.exception.DatabaseException;
import com.revature.model.User;

public class TestLoginService {
	
	private AuthenticationService loginService;
	private LoginDAO loginDao;

	@BeforeEach
	public void setUp() throws Exception {
		this.loginDao = mock(LoginDAO.class); //using mockito to creat fake user doa object
		
		this.loginService = new AuthenticationService(); //injecting mocked object into userservice object
	}
	
	
	@Test
	public void test_login_positive() throws DatabaseException, SQLException, BadParameterException {

		LoginDTO dto = new LoginDTO();
		dto.setUsername("bivy");
		dto.setPassword("1234bi");
		
		
		when(loginDao.login(eq(dto))).thenReturn(new User("bivy", "1234bi", "bill", "ivy"));
		
		User actual = loginDao.login(dto);
		
		User expected = new User("bivy", "1234bi", "bill", "ivy");
		
		assertEquals(expected.getUsername(), actual.getUsername());
		
	}
	
	
	@Test
	public void test_login_blankUsernamePassword() throws DatabaseException, SQLException, BadParameterException, LoginException {

		LoginDTO dto = new LoginDTO();
		dto.setUsername("");
		dto.setPassword("");
		
		
		try {
			loginService.login(dto);
			
			fail();
		} catch (BadParameterException e) {
			assertEquals("Username and Password field is blank!", e.getMessage());
		}
		
	}
	
	@Test
	public void test_login_spacesUsernamePassword() throws DatabaseException, SQLException, BadParameterException, LoginException {

		LoginDTO dto = new LoginDTO();
		dto.setUsername("          ");
		dto.setPassword("          ");
		
		
		try {
			loginService.login(dto);
			
			fail();
		} catch (BadParameterException e) {
			assertEquals("Username and Password field is blank!", e.getMessage());
		}
		
	}
	
	@Test
	public void test_login_blankUsername() throws DatabaseException, SQLException, BadParameterException, LoginException {

		LoginDTO dto = new LoginDTO();
		dto.setUsername("");
		dto.setPassword("1234bi");
		
		
		try {
			loginService.login(dto);
			
			fail();
		} catch (BadParameterException e) {
			assertEquals("Username field is blank!", e.getMessage());
		}
		
	}
	
	@Test
	public void test_login_spacesUsername() throws DatabaseException, SQLException, BadParameterException, LoginException {

		LoginDTO dto = new LoginDTO();
		dto.setUsername("      ");
		dto.setPassword("1234bi");
		
		
		try {
			loginService.login(dto);
			
			fail();
		} catch (BadParameterException e) {
			assertEquals("Username field is blank!", e.getMessage());
		}
		
	}
	
	
	@Test
	public void test_login_blankPassword() throws DatabaseException, SQLException, BadParameterException, LoginException {

		LoginDTO dto = new LoginDTO();
		dto.setUsername("bivy");
		dto.setPassword("");
		
		
		try {
			loginService.login(dto);
			
			fail();
		} catch (BadParameterException e) {
			assertEquals("Password field is blank!", e.getMessage());
		}
		
	}
	
	
	@Test
	public void test_login_spacesPassword() throws DatabaseException, SQLException, BadParameterException, LoginException {

		LoginDTO dto = new LoginDTO();
		dto.setUsername("bivy");
		dto.setPassword("     ");
		
		
		try {
			loginService.login(dto);
			
			fail();
		} catch (BadParameterException e) {
			assertEquals("Password field is blank!", e.getMessage());
		}
		
	}
	
	@Test
	public void test_login_wrongUsernamePassword() throws DatabaseException, SQLException, BadParameterException, LoginException {

		LoginDTO dto = new LoginDTO();
		dto.setUsername("not");
		dto.setPassword("correct");
		
		
		
		
		try {
			loginService.login(dto);
			
			fail();
		} catch (LoginException e) {
			assertEquals("Incorrect credentials provided", e.getMessage());
		}
		
	}
	

}
