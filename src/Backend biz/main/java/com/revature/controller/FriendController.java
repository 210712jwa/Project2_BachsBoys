package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.AddFriendToUserDTO;
import com.revature.dto.MessageDTO;
import com.revature.exception.BadParameterException;
import com.revature.model.Friend;
import com.revature.model.User;
import com.revature.service.FriendService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class FriendController {

	@Autowired
	private FriendService friendService;
	
	@Autowired
	private HttpServletRequest request;
	
	@PostMapping(path = "/addFriend", produces = "application/json")
	public ResponseEntity<Object> addFriendToUser(@RequestBody AddFriendToUserDTO addFriendToUserDTO){
		try {
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("currentUser");
			Friend friend = friendService.addFriendToUser(addFriendToUserDTO, user);
			
			return ResponseEntity.status(201).body(friend);
			
		} catch(BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}
	}
	
	@GetMapping(path = "/getAllFriends", produces = "application/json")
	public ResponseEntity<Object> getAllFriends(){
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("currentUser");
			List<Friend> friends = friendService.getAllFriends(user);
			return ResponseEntity.status(200).body(friends);
		
	}
}