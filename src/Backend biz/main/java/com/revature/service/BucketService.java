package com.revature.service;

import java.util.List;
import java.util.Set;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.BucketDAO;
import com.revature.dao.UserDAO;
import com.revature.dto.AddBucketDTO;
import com.revature.dto.AddBucketToUserDTO;
import com.revature.exception.BadParameterException;
import com.revature.exception.DatabaseException;
import com.revature.model.Bucket;
import com.revature.model.User;

@Service
public class BucketService {

	@Autowired
	private BucketDAO bucketDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	public Bucket addBucket(AddBucketDTO addBucketDTO) throws BadParameterException{
		if(addBucketDTO.getName().trim().equals("")) {
			throw new BadParameterException("Name field is blank!");
		}
		if(addBucketDTO.getCity().trim().equals("") ) {
			throw new BadParameterException("City field is blank!");
		}
		if(addBucketDTO.getCountry().trim().equals("")) {
			throw new BadParameterException("Country field is blank!");
		}
		Bucket bucket = bucketDAO.addBucket(addBucketDTO);
		return bucket;
	}

	public Bucket addBucketToUser(User user, AddBucketToUserDTO addBucketToUserDTO)  throws BadParameterException, LoginException, DatabaseException {
		if (user == null) {
			throw new LoginException("You must be logged in to add a bucket!");
		}
		if(bucketDAO.getBucketById(addBucketToUserDTO.getBucketId()) == null){
			throw new DatabaseException("This Bucket does not exist!");
		}
		Bucket bucket = bucketDAO.addBucketToUser(user, addBucketToUserDTO);
		return bucket;
	}

	public Bucket getBucketByCity(String city) throws DatabaseException {
		Bucket bucket = bucketDAO.getBucketByCity(city);
		if(bucket == null) {
			throw new DatabaseException("There are no buckets for city: " + city + "!");
		}
		return bucket;
	}
	
	public List<Bucket> getBucketByCountry(String country) throws DatabaseException {
		List<Bucket> buckets = bucketDAO.getBucketByCountry(country);
		if(buckets == null) {
			throw new DatabaseException("There are no buckets for country: " + country + "!");
		}
		return buckets;
	}

	public List<Bucket> getAllBuckets() throws DatabaseException {
		List<Bucket> buckets = bucketDAO.getAllBuckets();
		if(buckets == null) {
			throw new DatabaseException("There are no buckets in the database right now!");
		}
		return buckets;
	}

	public boolean checkBucket(User user, Bucket bucket) {
		boolean isAdded = false;
		Set<Bucket> userBuckets = userDAO.getUserBuckets(user);
		for(Bucket b: userBuckets ) {
			if(b.getId()==bucket.getId()) {
				isAdded = true;
			}
		}
		
		return isAdded;
	}

	public Set<Bucket> getAllBucketsFromUser(Integer id) {
		Set<Bucket> buckets= bucketDAO.getAllBucketsFromUser(id);
		return buckets;
	}

}
