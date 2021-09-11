package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dto.AddBucketToUserDTO;
import com.revature.dto.AddFriendToUserDTO;
import com.revature.dto.AddUserDTO;
import com.revature.model.Bucket;
import com.revature.model.Friend;
import com.revature.model.User;

@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public User addUser(AddUserDTO addUserDTO) {
		Session session = sessionFactory.getCurrentSession();
		
		User newUser = new User(addUserDTO.getUsername(), addUserDTO.getPassword(), addUserDTO.getFirstName(), addUserDTO.getLastName());
		session.persist(newUser);
		
		return newUser;
	}

	@Transactional
	public List<User> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = session.createQuery("Select s FROM User s").getResultList();
		return users;
	}

	
	@Transactional
	public User getUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.createQuery("Select s FROM User s WHERE s.username = :username").setParameter("username", username).getSingleResult();
		return user;
	}
	
	@Transactional
	public Set<Bucket> getUserBuckets(User user) {
		Session session = sessionFactory.getCurrentSession();
		Set<Bucket> buckets = user.getBuckets();
		return buckets;
	}
}
