package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.AddBucketDTO;
import com.revature.dto.AddBucketToUserDTO;
import com.revature.dto.MessageDTO;
import com.revature.exception.BadParameterException;
import com.revature.model.Bucket;
import com.revature.model.User;
import com.revature.service.BucketService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class BucketController {
	
	@Autowired
	private BucketService bucketService;
	
	@Autowired
	private HttpServletRequest request;
	
	@PostMapping(path = "/addBucket", produces = "application/json")
	public ResponseEntity<Object> addBucket(@RequestBody AddBucketDTO addBucketDTO){
		try {
			Bucket bucket = bucketService.addBucket(addBucketDTO);
			
			return ResponseEntity.status(201).body(bucket);
		
		} catch( BadParameterException e) {
			
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}
	}
	
	@PostMapping(path = "/addBucketToUser", produces = "application/json")
	public ResponseEntity<Object> addBucketToUser(@RequestBody AddBucketToUserDTO addBucketToUserDTO){
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("currentUser");
			Bucket bucket = bucketService.addBucketToUser(user, addBucketToUserDTO);
			return ResponseEntity.status(201).body(bucket);
		} catch(BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}
		
		
	}
	
	@GetMapping(path = "/getBucketByCity", produces = "application/json")
	public ResponseEntity<Object> getBucketByCity(@RequestParam(name="city") String city){
		Bucket bucket = bucketService.getBucketByCity(city);
		return ResponseEntity.status(200).body(bucket);
		
	}
	
	@GetMapping(path = "/getBucketByCountry", produces = "application/json")
	public ResponseEntity<Object> getBucketByCountry(@RequestParam(name="country") String country){
		List<Bucket> buckets = bucketService.getBucketByCountry(country);
		return ResponseEntity.status(200).body(buckets);
		
	}
		
	@GetMapping(path = "/getAllBuckets", produces = "application/json")
	public ResponseEntity<Object> getAllBuckets(){
			List<Bucket> buckets = bucketService.getAllBuckets();
			return ResponseEntity.status(200).body(buckets);
	}
}
