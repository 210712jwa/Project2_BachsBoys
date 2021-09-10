package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	

	@GetMapping(path = "/getAllBuckets", produces = "application/json")
	public ResponseEntity<Object> getAllBuckets(){
		try {
			List<Bucket> buckets = userService.getAllBuckets();
			
			return ResponseEntity.status(200).body(buckets);
		} catch(BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}
	}
	
	
	@PatchMapping(path = "/addBucketToUser", produces = "application/json")
	public ResponseEntity<Object> addBucketToUser(@RequestBody AddBucketToUserDTO addBucketToUserDTO) {
		try {
			Bucket bucket = userService.addBucketToUser(addBucketToUserDTO);
			
			return ResponseEntity.status(201).body(bucket);
		} catch(BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}
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
