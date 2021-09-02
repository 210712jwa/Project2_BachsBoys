package com.revature.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dto.LoginDTO;
import com.revature.model.User;

@Repository
public class LoginDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public User login(LoginDTO loginDTO) {
		Session session = sessionFactory.getCurrentSession();
		
		
		try {
			User user = (User) session.createQuery("FROM User u WHERE u.username = :username AND u.password = :password")
					.setParameter("username", loginDTO.getUsername())
					.setParameter("password", loginDTO.getPassword())
					.getSingleResult();
			System.out.println(user.toString());
			
			return user;
		} catch(NoResultException e) {			
			return null;
		} 
	}

}
