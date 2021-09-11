package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.BucketDAO;
import com.revature.dto.AddBucketDTO;
import com.revature.dto.AddBucketToUserDTO;
import com.revature.exception.BadParameterException;
import com.revature.model.Bucket;
import com.revature.model.User;

@Service
public class BucketService {

	@Autowired
	private BucketDAO bucketDAO;
	
	public Bucket addBucket(AddBucketDTO addBucketDTO) throws BadParameterException{
		Bucket bucket = bucketDAO.addBucket(addBucketDTO);
		return bucket;
	}

	public Bucket addBucketToUser(User user, AddBucketToUserDTO addBucketToUserDTO)  throws BadParameterException {
		Bucket bucket = bucketDAO.addBucketToUser(user, addBucketToUserDTO);
		return bucket;
	}

	public Bucket getBucketByCity(String city) {
		Bucket bucket = bucketDAO.getBucketByCity(city);
		return bucket;
	}
	
	public List<Bucket> getBucketByCountry(String country) {
		List<Bucket> buckets = bucketDAO.getBucketByCountry(country);
		return buckets;
	}

	public List<Bucket> getAllBuckets() {
		List<Bucket> buckets = bucketDAO.getAllBuckets();
		return buckets;
	}

}
