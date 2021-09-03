package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BucketDestination")
public class BucketDestination {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "location name")
	private String locationName;
	
	@Column(name = "description")
	private String description;

	public BucketDestination() {
		super();
	}

	public BucketDestination(int id, String locationName, String description) {
		super();
		this.id = id;
		this.locationName = locationName;
		this.description = description;
	}
}
