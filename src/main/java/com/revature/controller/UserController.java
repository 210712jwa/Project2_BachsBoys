package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.AddUserDTO;
import com.revature.dto.MessageDTO;
import com.revature.exception.BadParameterException;
import com.revature.model.User;
import com.revature.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;

	@PostMapping(path = "/addUser")
	public ResponseEntity<Object> addUser(@RequestBody AddUserDTO addUserDTO) {
		try {
			User user = userService.addUser(addUserDTO);

			return ResponseEntity.status(201).body(user);
		} catch (BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}
	}

	@GetMapping(path = "/currentUser")
	public ResponseEntity<Object> getCurrentUser() {
		HttpSession session = request.getSession();
		Object user = session.getAttribute("currentUser");
		
		return ResponseEntity.status(201).body(user);
	}
}
