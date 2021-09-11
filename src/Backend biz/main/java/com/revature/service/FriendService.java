package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.FriendDAO;
import com.revature.dto.AddFriendToUserDTO;
import com.revature.exception.BadParameterException;
import com.revature.model.Friend;
import com.revature.model.User;

@Service
public class FriendService {
	
	@Autowired
	private FriendDAO friendDAO;
	
	public Friend addFriendToUser(AddFriendToUserDTO addFriendToUserDTO,  User user) throws BadParameterException {
		Friend friend = friendDAO.addFriendToUser(addFriendToUserDTO, user);
		return friend;
	}

	public List<Friend> getAllFriends(User user) {
		List<Friend> friends = friendDAO.getAllFriends(user);
		return friends;
	}

}
