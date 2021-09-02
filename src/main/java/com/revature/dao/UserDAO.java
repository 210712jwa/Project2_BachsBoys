package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dto.AddUserDTO;
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

}
