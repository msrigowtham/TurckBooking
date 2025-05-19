package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.BookingModel;
import com.example.demo.model.UsersModel;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UsersModel updatedUser) {
	    UsersModel user = service.updateUser(id, updatedUser);
	    if (user == null) {
	        return ResponseEntity.status(404).body("User not found");
	    }
	    return ResponseEntity.ok("User updated successfully!");
	}
	
	
	@GetMapping("/signin-user")
	public ResponseEntity<Map<String, Object>> getUser(@RequestParam String userMail){
	Map<String,Object> response = new HashMap<>();
		
		if(userMail != null) {
			
			UsersModel user = service.getUser(userMail);
			
			if(user == null) {
				response.put("message", "Driver not fount for the given details");
				response.put("Status",HttpStatus.NOT_FOUND);
			}
			
			else {
			response.put("message",user);
			response.put("Status",HttpStatus.OK);
			}
		}
		
		
		else {
			response.put("message", "Some credentials are missing");
			response.put("Status",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/book-vehicle")
	public ResponseEntity<Map<String, Object>> getNearVehicles(@RequestBody BookingModel booking){
		Map<String, Object> response = new HashMap<>();
		
		List<BookingModel> bookingResponse = service.getVehicle(booking);
		
		if(bookingResponse == null) {
			response.put("message", "Vehicles not fount for the ride");
			response.put("Status",HttpStatus.NOT_FOUND);
		}
		
		else {
			response.put("vehicles", bookingResponse);
			response.put("status", HttpStatus.OK);
			
		}
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		
	}
	
	@PostMapping("/request-vehicle")
	public ResponseEntity<Map<String, Object>> requetsVehicle(@RequestBody BookingModel booking){
		Map<String, Object> response = new HashMap<>();
		
		BookingModel bookResponse = service.requestVehicle(booking);
		
		if(bookResponse == null) {
			response.put("message","Invalid booking request");
			response.put("Status", HttpStatus.BAD_REQUEST);
		}
		
		else {
			response.put("Booking", bookResponse);
			response.put("Status", HttpStatus.PROCESSING);
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
}
