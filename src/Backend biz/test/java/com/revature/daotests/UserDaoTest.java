package com.revature.daotests;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.revature.dao.UserDAO;
import com.revature.dto.AddUserDTO;
import com.revature.model.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:springorm-test.properties")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)

public class UserDaoTest {
	
	@Autowired
	private UserDAO userDao;
	
	@Test
	@Transactional
	@Order(0)
	@Commit
	void testAddUser_hasAutoGereatedId() {
		AddUserDTO dto = new AddUserDTO();
		dto.setFirstName("bill");
		dto.setLastName("ivy");
		dto.setUsername("bivy");
		dto.setPassword("1234bi");
		
		User user = userDao.addUser(dto);
		
		assertEquals(1, user.getId());
	}
	
	

}