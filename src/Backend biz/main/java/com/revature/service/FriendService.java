package com.revature.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.FriendDAO;
import com.revature.dao.UserDAO;
import com.revature.dto.AddFriendToUserDTO;
import com.revature.exception.BadParameterException;
import com.revature.exception.DatabaseException;
import com.revature.model.Friend;
import com.revature.model.User;

@Service
public class FriendService {
	
	@Autowired
	private FriendDAO friendDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	public Friend addFriendToUser(AddFriendToUserDTO addFriendToUserDTO,  User user) throws  DatabaseException {
		if(userDAO.getUserById(addFriendToUserDTO.getFriendId()) == null) {
			throw new DatabaseException("User does not exist to add as friend!");
		}
		Friend friend = friendDAO.addFriendToUser(addFriendToUserDTO, user);
		return friend;
	}

	public List<Friend> getAllFriends(User user) throws DatabaseException, LoginException {
		if(user == null) {
			throw new LoginException("You must be logged in to access your friends!");
		}
		List<Friend> friends = friendDAO.getAllFriends(user);
		if(friends == null) {
			throw new DatabaseException("You have no friends!");
		}
		return friends;
	}

	public Friend checkFriend(User user, User friend) {
		Friend link = friendDAO.checkFriend(user ,friend);
		return link;
	}



}
