package com.revature.daotests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dao.FriendDAO;
import com.revature.dao.LoginDAO;
import com.revature.dao.UserDAO;
import com.revature.dto.AddFriendToUserDTO;
import com.revature.dto.AddUserDTO;
import com.revature.dto.LoginDTO;
import com.revature.model.Friend;
import com.revature.model.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:springorm-test.properties")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)

public class LoginDaoTest {
	
	@Autowired
	private LoginDAO loginDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Test
	@Transactional
	@Order(0)
	@Commit
	void testLogin_success() {
		
		AddUserDTO dto = new AddUserDTO();
		dto.setFirstName("bill");
		dto.setLastName("ivy");
		dto.setUsername("bivy");
		dto.setPassword("1234bi");
		
		User user1 = userDao.addUser(dto);
		
		LoginDTO dfo = new LoginDTO();
		dfo.setUsername("bivy");
		dfo.setPassword("1234bi");
		
		
		User user2 = loginDao.login(dfo);
		
		assertEquals(user1, user2);
	}
	
	
	@Test
	@Transactional
	@Order(1)
	@Commit
	void testLogin_failure() {
		
		User user1 = null;
		
		LoginDTO dfo = new LoginDTO();
		dfo.setUsername("wrong");
		dfo.setPassword("info");
		
		User user2 = loginDao.login(dfo);
		
		assertEquals(user1, user2);
	}
	

}
