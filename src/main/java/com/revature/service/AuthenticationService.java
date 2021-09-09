package com.revature.service;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.LoginDAO;
import com.revature.dao.UserDAO;
import com.revature.dto.LoginDTO;
import com.revature.exception.BadParameterException;
import com.revature.model.User;

@Service
public class AuthenticationService {
	
	@Autowired
	private LoginDAO loginDao;
	
	public User login(LoginDTO loginDTO) throws LoginException, BadParameterException {
		if(loginDTO.getUsername().trim().equals("") && loginDTO.getPassword().trim().equals("")) {
			throw new BadParameterException("Username and Password field is blank!");
		}
		else if(loginDTO.getUsername().trim().equals("")) {
			throw new BadParameterException("Username field is blank!");
			
		}
		else if(loginDTO.getPassword().trim().equals("")) {
			throw new BadParameterException("Password field is blank!");
		}
		User user = loginDao.login(loginDTO);
		
		if (user == null) {
			throw new LoginException("Incorrect credentials provided");
		}
		
		return user;
	}

}
