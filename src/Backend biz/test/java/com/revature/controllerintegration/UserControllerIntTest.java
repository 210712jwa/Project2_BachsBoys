package com.revature.controllerintegration;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDAO;
import com.revature.dto.AddUserDTO;
import com.revature.model.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:springorm-test.properties")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)

public class UserControllerIntTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;
	
	@Autowired
	private UserDAO userDao;
	
	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.objectMapper = new ObjectMapper();
	}

	@Test
	@Transactional
	@Order(0)
	@Commit
	void test_addUser_andReceiveJsonResponse() throws Exception {
		
		AddUserDTO dto = new AddUserDTO();
		dto.setFirstName("bill");
		dto.setLastName("ivy");
		dto.setUsername("bivy");
		dto.setPassword("1234bi");
		
		String addUserDtoJson = objectMapper.writeValueAsString(dto);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/addUser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(addUserDtoJson);
		
		User expected = new User("bivy", "1234bi", "bill", "ivy");
		expected.setId(1);
		String expectedJson = objectMapper.writeValueAsString(expected);
		
		this.mockMvc
			.perform(builder)
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.content().json(expectedJson));
	}
	
	@Test
	@Transactional
	@Order(1)
	@Commit
	void test_getUserByUsername_andReceiveJsonResponse() throws Exception {
		
		AddUserDTO dto = new AddUserDTO();
		dto.setFirstName("bill");
		dto.setLastName("ivy");
		dto.setUsername("bivy");
		dto.setPassword("1234bi");
		
		String getUserByUsernameDtoJson = objectMapper.writeValueAsString(dto);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.get("/getUserByUsername?username=bivy")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getUserByUsernameDtoJson);
		
		User expected = new User("bivy", "1234bi", "bill", "ivy");
		expected.setId(1);
		String expectedJson = objectMapper.writeValueAsString(expected);
		
		this.mockMvc
			.perform(builder)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().json(expectedJson));
	}
	
	
	
//	@Test
//	@Transactional
//	@Order(2)
//	@Commit
//	void test_getAllUsers_andReceiveJsonResponse() throws Exception {
//		
//		AddUserDTO dto = new AddUserDTO();
//		dto.setFirstName("bill");
//		dto.setLastName("ivy");
//		dto.setUsername("bivy");
//		dto.setPassword("1234bi");
//		
//		String getAllUsersDtoJson = objectMapper.writeValueAsString(dto);
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
//				.get("/getAllUsers")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(getAllUsersDtoJson);
//		
//		User expected = new User("bivy", "1234bi", "bill", "ivy");
//		expected.setId(1);
//		
//		List<User> expectedarr = new ArrayList<>();
//		
//		expectedarr.add(expected);
//		
//		String expectedJson = objectMapper.writeValueAsString(expected);
//		
//		this.mockMvc
//			.perform(builder)
//			.andExpect(MockMvcResultMatchers.status().isOk())
//			.andExpect(MockMvcResultMatchers.content().json(expectedJson));
//	}
//	
	
	

}
