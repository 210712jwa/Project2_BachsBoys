package com.revature.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.AddBucketToUserDTO;
import com.revature.dto.AddFriendToUserDTO;
import com.revature.dto.AddUserDTO;
import com.revature.dto.MessageDTO;
import com.revature.exception.BadParameterException;
import com.revature.model.Bucket;
import com.revature.model.Friend;
import com.revature.model.User;
import com.revature.service.UserService;



@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;

	
	@PostMapping(path = "/addUser", produces = "application/json")
	public ResponseEntity<Object> addUser(@RequestBody AddUserDTO addUserDTO) {
		try {
			User user = userService.addUser(addUserDTO);

			return ResponseEntity.status(201).body(user);
		} catch (BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}
	}
	
	@GetMapping(path = "/getAllUsers", produces = "application/json")
	public ResponseEntity<Object> getAllUsers(){
		
		List<User> users = userService.getAllUsers();
		return ResponseEntity.status(200).body(users);
		
	}
	
	@GetMapping(path = "/getUserByUsername", produces = "application/json")
	public ResponseEntity<Object> getUserByUsername(@RequestParam(name="username") String username){
		User user = userService.getUserByUsername(username);
		return ResponseEntity.status(200).body(user);
		
	}


	@GetMapping(path = "/getUserBuckets", produces = "application/json")
	public ResponseEntity<Object> getUserBuckets(){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		Set<Bucket> buckets = userService.getUserBuckets(user);
		return ResponseEntity.status(200).body(buckets);
	}
	
	
	
	@GetMapping(path = "/currentUser")
	public ResponseEntity<Object> getCurrentUser() {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		
		if(user == null) {
			return ResponseEntity.status(400).body(new MessageDTO("no user currently logged in!"));
		} else {
			return ResponseEntity.status(201).body(user);
		}
	}
	


}
