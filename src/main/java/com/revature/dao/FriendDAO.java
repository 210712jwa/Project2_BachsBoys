package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dto.AddFriendToUserDTO;
import com.revature.model.Friend;
import com.revature.model.User;

@Repository
public class FriendDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public Friend addFriendToUser(AddFriendToUserDTO addFriendToUserDTO, User user) {
		Session session = sessionFactory.getCurrentSession();
		Friend newFriend = new Friend();
		newFriend.setUser(user);
		newFriend.setFriend(session.get(User.class, addFriendToUserDTO.getFriendId()));
		session.persist(newFriend);
		return newFriend ;
	}

	public List<Friend> getAllFriends(User user) {
		Session session = sessionFactory.getCurrentSession();
		List<Friend> friends = session.createQuery("Select s FROM friend s JOIN s.user u WHERE u.id = :userid").setParameter("userid", user.getId()).getResultList();
		
		return friends;
	}

}
