package com.revature.controller;

import java.util.List;
import java.util.Set;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.AddBucketDTO;
import com.revature.dto.AddBucketToUserDTO;
import com.revature.dto.MessageDTO;
import com.revature.exception.BadParameterException;
import com.revature.exception.DatabaseException;
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
		} catch(LoginException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		} catch(DatabaseException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}
		
		
	}
	
	@GetMapping(path = "/getCurrentBucket", produces = "application/json")
	public ResponseEntity<Object> getCurrentBucket(){
		HttpSession session = request.getSession();
		Bucket bucket = (Bucket) session.getAttribute("currentBucket");
		
		if(bucket == null) {
			return ResponseEntity.status(400).body(new MessageDTO("no bucket searched!"));
		} else {
			return ResponseEntity.status(200).body(bucket);
		}
		
	}
	@GetMapping(path = "/getBucketByCity", produces = "application/json")
	public ResponseEntity<Object> getBucketByCity(@RequestParam(name="city") String city){
		try {
			Bucket bucket = bucketService.getBucketByCity(city);
			HttpSession session = request.getSession();
			session.setAttribute("currentBucket", bucket);
			return ResponseEntity.status(200).body(bucket);
			
		}catch(DatabaseException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}			
	}
	
	@GetMapping(path = "/getBucketByCountry", produces = "application/json")
	public ResponseEntity<Object> getBucketByCountry(@RequestParam(name="country") String country){
		try {
			List<Bucket> buckets = bucketService.getBucketByCountry(country);
			return ResponseEntity.status(200).body(buckets);
			
		}catch(DatabaseException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}		
	}
		
	@GetMapping(path = "/getAllBuckets", produces = "application/json")
	public ResponseEntity<Object> getAllBuckets(){
		try {
			List<Bucket> buckets = bucketService.getAllBuckets();
			return ResponseEntity.status(200).body(buckets);
			
		}catch(DatabaseException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}
			
	}
	@GetMapping(path = "/getAllBucketsForUser/{id}", produces = "application/json")
	public ResponseEntity<Object> getAllBucketsFromUser(@PathVariable Integer id){
		Set<Bucket> buckets = bucketService.getAllBucketsFromUser(id);
		return ResponseEntity.status(200).body(buckets);
	}
	
	@GetMapping(path = "/checkBucket",produces = "application/json")
	public ResponseEntity<Object> checkBucket(){
		HttpSession session = request.getSession();
		Bucket bucket = (Bucket) session.getAttribute("currentBucket");
		User user = (User) session.getAttribute("currentUser");
		boolean isAdded = bucketService.checkBucket(user,bucket);
		System.out.println(isAdded);
		if(isAdded) {
			return ResponseEntity.status(200).body(bucket);
		} else {
			return ResponseEntity.status(400).body(new MessageDTO("Bucket Already Added"));
		}
		
	}
}
