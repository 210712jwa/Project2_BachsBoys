package com.revature.controller;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.AuthenticationService;
import com.revature.service.LoginService;
import com.revature.exception.BadParameterException;
import com.revature.model.User;
import com.revature.dto.LoginDTO;
import com.revature.dto.MessageDTO;


		
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	
	@PostMapping(path = "/login", produces = "application/json")
	public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO){
		try {
			User user = this.authService.login(loginDTO);
			
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", user);
			
			
			return ResponseEntity.status(200).body(user);
		} catch (LoginException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		} catch(BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}
	}
	
	@PostMapping(path = "/logout")
	public ResponseEntity<Object> logout(@RequestBody LoginDTO loginDTO){
		try {
			User user = loginService.login(loginDTO);
			
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", user);
			
			
			return ResponseEntity.status(200).body(user);
		} catch (BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}
	}
	
	@PostMapping(path = "/logout")
	public ResponseEntity<Object> logout(){
	
			HttpSession session = request.getSession(false);
			
			if(session == null || session.getAttribute("currentUser") == null) {
				return ResponseEntity.status(400).body(new MessageDTO("You are not logged in"));
			}
			
			session.invalidate();
			return ResponseEntity.status(200).body(new MessageDTO("Successfully logged out "));
		
	}

}
