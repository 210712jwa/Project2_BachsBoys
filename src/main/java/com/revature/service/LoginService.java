package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.LoginDAO;
import com.revature.dto.LoginDTO;
import com.revature.exception.BadParameterException;
import com.revature.model.User;

@Service
public class LoginService {
	
	@Autowired
	private LoginDAO loginDAO;


	public User login(LoginDTO loginDTO) throws BadParameterException {
		if(loginDTO.getUsername().trim().equals("") && loginDTO.getPassword().trim().equals("")) {
			throw new BadParameterException("Username and Password field is blank!");
		}
		else if(loginDTO.getUsername().trim().equals("")) {
			throw new BadParameterException("Username field is blank!");
			
		}
		else if(loginDTO.getPassword().trim().equals("")) {
			throw new BadParameterException("Password field is blank!");
		}
		User user = loginDAO.login(loginDTO);
		
		return user;
	}

}
