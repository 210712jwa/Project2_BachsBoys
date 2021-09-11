package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.UserDAO;
import com.revature.dto.AddBucketToUserDTO;
import com.revature.dto.AddFriendToUserDTO;
import com.revature.dto.AddUserDTO;
import com.revature.exception.BadParameterException;
import com.revature.model.Bucket;
import com.revature.model.Friend;
import com.revature.model.User;

@Service
public class UserService {
	
	private UserDAO userDAO;
	
	@Autowired
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User addUser(AddUserDTO addUserDTO) throws BadParameterException {
		if(addUserDTO.getUsername().trim().equals("")) {
			throw new BadParameterException("Username field is blank!");
		}
		if(addUserDTO.getPassword().trim().equals("")) {
			throw new BadParameterException("Password field is blank!");
		}
		if(addUserDTO.getFirstName().trim().equals("")) {
			throw new BadParameterException("First Name field is blank!");
		}
		if(addUserDTO.getLastName().trim().equals("")) {
			throw new BadParameterException("Last Name field is blank!");
		}
		
		User user = userDAO.addUser(addUserDTO);
		return user;
	}

	public List<Bucket> getAllBuckets() throws BadParameterException{
		List<Bucket> buckets = userDAO.getAllBuckets();
		return buckets;
	}

	public Bucket addBucketToUser(AddBucketToUserDTO addBucketToUserDTO) throws BadParameterException {
		Bucket bucket = userDAO.addBucketToUser(addBucketToUserDTO);
		return bucket;
	}

	public List<User> getAllUsers() {
		List<User> users = userDAO.getAllUsers();
		return users;
	}

	public User getUserByUsername(String username) {
		User user = userDAO.getUserByUsername(username);
		return user;
	}

	

	
}