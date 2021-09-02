package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.LoginService;
import com.revature.exception.BadParameterException;
import com.revature.model.User;
import com.revature.dto.LoginDTO;
import com.revature.dto.MessageDTO;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@PostMapping(path = "/login")
	public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO){
		try {
			User user = loginService.login(loginDTO);
			
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", user);
			
			
			return ResponseEntity.status(200).body(user);
		} catch (BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}
	}

}
