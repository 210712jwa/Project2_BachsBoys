package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dto.AddBucketDTO;
import com.revature.dto.AddBucketToUserDTO;
import com.revature.model.Bucket;
import com.revature.model.Friend;
import com.revature.model.User;

@Repository
public class BucketDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public Bucket addBucket(AddBucketDTO addBucketDTO) {
		System.out.println("here");
		Session session;
			session = sessionFactory.getCurrentSession();
		
		
		Bucket bucket = new Bucket();
		bucket.setCity(addBucketDTO.getCity());
		bucket.setCountry(addBucketDTO.getCountry());
		bucket.setName(addBucketDTO.getName());
		session.persist(bucket);
		return bucket;
	}
	
	@Transactional
	public Bucket addBucketToUser(User user, AddBucketToUserDTO addBucketToUserDTO) {
		Session session = sessionFactory.getCurrentSession();
		Bucket bucket = session.get(Bucket.class, addBucketToUserDTO.getBucketId());
		user.addBucket(bucket);
		session.saveOrUpdate(user);
		return bucket;	
	}
	
	@Transactional
	public List<Bucket> getAllBuckets(){
		Session session = sessionFactory.getCurrentSession();
		List<Bucket> buckets = session.createQuery("Select s FROM Bucket s").getResultList();
		return buckets;
	}

	@Transactional
	public Bucket getBucketByCity(String city) {
		
		Session session = sessionFactory.getCurrentSession();
		Bucket bucket = (Bucket) session.createQuery("Select s FROM Bucket s WHERE s.city = :city").setParameter("city", city).getSingleResult();
		return bucket;													
	}
	
	@Transactional
	public List<Bucket> getBucketByCountry(String country) {
		
		Session session = sessionFactory.getCurrentSession();
		List<Bucket> buckets = session.createQuery("Select s FROM Bucket s WHERE s.country = :country").setParameter("country", country).getResultList();
		return buckets;
	}
}
