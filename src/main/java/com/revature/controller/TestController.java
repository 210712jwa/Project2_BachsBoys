package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class TestController {
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/hello", produces = "application/json" )
	public @ResponseBody String hello() {
		return "Hello World!";
	}

}
