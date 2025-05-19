package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.DriverModel;
import com.example.demo.model.UsersModel;
import com.example.demo.service.DriverService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private UserService service;
	
	
	@Autowired
	private DriverService drservice;

	
	@PostMapping("/register-user")
	public ResponseEntity<Map<String,Object>> saveUsers(@RequestBody UsersModel user){
		
		Map<String,Object> response = new HashMap<>();
		
		if(user.getFname() != null || user.getLname() != null || user.getEmail() != null || 
				user.getPassword()!= null || user.getDOB() != null) {
			UsersModel model = service.saveUsers(user);
			response.put("message", model);
			response.put("Status",HttpStatus.CREATED);
		}
		
		else {
			response.put("message", "Some credentials are missing");
			response.put("Status",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	
	@PostMapping("/register-driver")
	public ResponseEntity<Map<String,Object>> saveVehicle(@RequestBody DriverModel driver){
           Map<String,Object> response = new HashMap<>();
		
		if(driver.getFname() != null && driver.getLname() != null && driver.getDOB() != null && driver.getEmail() != null &&
				driver.getPassword() != null && driver.getRole() != null) 
		{
			driver.setAvailable(true);
			
			drservice.saveDriver(driver);
			response.put("message","Driver Registered successfully");
			response.put("Status",HttpStatus.CREATED);
		}
		
		else {
			response.put("message", driver);
			response.put("Status",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
