package com.revature.controllerunit;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controller.UserController;
import com.revature.dto.MessageDTO;
import com.revature.exception.BadParameterException;
import com.revature.model.User;
import com.revature.service.UserService;


@ExtendWith(MockitoExtension.class)
public class UserControllerUnitTest {
	
private static MockMvc mockMvc;
	
	@Mock
	private UserService userServiceMock;
	
	@InjectMocks // This will instantiate the ShipController and inject the shipService mock into it
	private UserController userController;
	
	@BeforeEach
	void setUp() throws Exception {		
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

//	@Test
//	void testGetUserByUsername1_success() throws Exception {
//		
//		User user1 = new User("bivy", "1234bi", "bill", "ivy");
//		user1.setId(1);
//		
//		
//		
//		when(userServiceMock.getUserByUsername(eq("bivy"))).thenReturn(user1);
//		
//		User expectedUser = new User("bivy", "1234bi", "bill", "ivy");
//		expectedUser.setId(1);
//		
//		ObjectMapper om = new ObjectMapper();
//		String expectedUserJson = om.writeValueAsString(expectedUser); // converting our expected object into JSON
//		
//		mockMvc
//			.perform(get("/getUserByUsername?username=bivy"))
//			.andExpect(MockMvcResultMatchers.status().isOk()) // expecting status code 200
//			.andExpect(MockMvcResultMatchers.content().json(expectedUserJson)); // expecting our JSON
//	}
	
	
//	@Test
//	void testGetShipByIdabc_400() throws Exception {
//		BadParameterException bpException = new BadParameterException("Id must be an int");
//		when(userServiceMock.getUserByUsername(eq("abc"))).thenThrow(bpException);
//		
//		MessageDTO expectedDto = new MessageDTO("Id must be an int");
//		
//		ObjectMapper om = new ObjectMapper();
//		String expectedDtoJson = om.writeValueAsString(expectedDto);
//		
//		mockMvc
//			.perform(get("/getUserByUsername?username=abc"))
//			.andExpect(MockMvcResultMatchers.status().is4xxClientError())
//			.andExpect(MockMvcResultMatchers.content().json(expectedDtoJson));
//	}
//	
	
	
//	@Test
//	void testGetCurrentUser_fail() throws Exception {
//		
//		User user1 = new User("bivy", "1234bi", "bill", "ivy");
//		user1.setId(1);
//		
//		when(userServiceMock.getUserByUsername(eq("bivy"))).thenReturn(user1);
//		
//		User expectedUser = null;
//		
//		ObjectMapper om = new ObjectMapper();
//		String expectedUserJson = om.writeValueAsString(expectedUser); // converting our expected object into JSON
//		
//		mockMvc
//			.perform(get("/currentUser"))
//			.andExpect(MockMvcResultMatchers.status().isNotFound()) // expecting status code 404
//			.andExpect(MockMvcResultMatchers.content().json(expectedUserJson)); // expecting our JSON
//	}

}
