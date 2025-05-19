package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BookingHistoryModel;
import com.example.demo.model.BookingModel;
import com.example.demo.model.DriverModel;
import com.example.demo.model.LocationModel;
import com.example.demo.model.VehicleModel;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {
	
	@Autowired
	private DriverService service;
	
	@Autowired
	private VehicleRepository vehiclerepo;
	
	
	
	
	
	
	@PutMapping("/driver/updateVehicle")
	public ResponseEntity<String> updateDriverVehicleDetails(@RequestBody VehicleModel updatedVehicle) {
	    VehicleModel vehicle = service.updateDriverVehicleDetails(updatedVehicle);
	    
	    if (vehicle == null) {
	        return ResponseEntity.status(404).body("Driver or Vehicle not found");
	    }
	    
	    return ResponseEntity.ok("Driver's vehicle details updated successfully!");
	}

	
	
	
	
	
	
	@PutMapping("/driver/update/{id}")
	public ResponseEntity<String> updateDriver(@PathVariable Long id, @RequestBody DriverModel updatedDriver) {
	    DriverModel driver = service.updateDriver(id, updatedDriver);
	    
	    if (driver == null) {
	        return ResponseEntity.status(404).body("Driver not found");
	    }
	    
	    return ResponseEntity.ok("Driver details updated successfully!");
	}
	
	
	
	
	
	@PostMapping("/register-vehicle")
	public ResponseEntity<Map<String, Object>> saveVehicle(@RequestBody VehicleModel vehicle){
		Map<String,Object> response = new HashMap<>();
		VehicleModel vehicleResponse = service.saveVehicle(vehicle);
		
		if(vehicleResponse == null) {
			response.put("message", "Driver not fount for the given details");
			response.put("Status",HttpStatus.NOT_FOUND);
		}
		
		else {
			response.put("vehicle", vehicle);
			response.put("Status",HttpStatus.CREATED);
			}
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}

	
	@GetMapping("/signin-driver")
	public ResponseEntity<Map<String,Object>> getDriver(@RequestParam String driverMail){
		Map<String,Object> response = new HashMap<>();
		
		if(driverMail != null) {
			
			DriverModel driver = service.getDriver(driverMail);
			
			if(driver == null) {
				response.put("message", "Driver not fount for the given details");
				response.put("Status",HttpStatus.NOT_FOUND);
			}
			
			else {
			VehicleModel vehicle = vehiclerepo.getByDriverId(driver.getId());
			
			response.put("driver",driver);
			response.put("vehicle", vehicle);
			response.put("Status",HttpStatus.OK);
			}
		}
		
		
		else {
			response.put("message", "Some credentials are missing");
			response.put("Status",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/change-availability")
	public ResponseEntity<Map<String,Object>> changeAvailability(@RequestParam String driverMail){
		Map<String, Object> response = new HashMap<>();
		
		if(driverMail != null) {
			DriverModel driver = service.changeAvailability(driverMail);
			
			if(driver == null) {
				response.put("message", "Driver not fount for the given details");
				response.put("Status",HttpStatus.NOT_FOUND);
			}
			else {
				response.put("message", driver);
				response.put("Status", HttpStatus.ACCEPTED);
			}
			
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		
	}
	
	@PostMapping("/update-location")
	public ResponseEntity<Map<String, Object>> updateLocation(@RequestBody LocationModel location){
		Map<String, Object> response = new HashMap<>();
		
		if(location.getLatitude() != 0.0 && location.getLatitude() != 0.0) {
			String msg = service.updateLocation(location);
			
			if(msg == null) {
				response.put("message", "Driver currently offline or Driver id not Found");
				response.put("Status",HttpStatus.BAD_REQUEST);
			}
			
			else {
				response.put("message", msg);
				response.put("status", HttpStatus.OK);
			}
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/fetch-booking")
	public ResponseEntity<Map<String, Object>> fetchBooking(@RequestParam Long driverId){
		Map<String, Object> response = new HashMap<>();
		
		BookingModel booking = service.fetchBooking(driverId);
		
		if(booking == null) {
			response.put("message","No bookings were available");
			response.put("status",HttpStatus.OK);
		}
		
		else {
			response.put("booking", booking);
			response.put("status",HttpStatus.OK);
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/confirm-booking")
	public ResponseEntity<Map<String, Object>> confirmBooking(@RequestBody BookingModel booking){
		Map<String, Object> response = new HashMap<>();
		
		    BookingModel bookingResponse = service.confirmBooking(booking);
			
			if(booking == null) {
				response.put("message","Invalid credentials");
				response.put("status", HttpStatus.BAD_REQUEST);
			}
			
			else {
				response.put("Booking", bookingResponse);
				response.put("status",HttpStatus.OK);
			}
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/end-ride")
	public ResponseEntity<Map<String, Object>> endRide(@RequestParam Long bookingId){
		Map<String, Object> response = new HashMap<>();
		
		BookingModel booking = service.endRide(bookingId);
		
		if(booking == null) {
			response.put("message", "Invalid Booking ID");
			response.put("status",HttpStatus.BAD_REQUEST);
		}
		
		else {
			response.put("booking", booking);
			response.put("status",HttpStatus.OK);
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/fetch-ridehistory")
	public ResponseEntity<Map<String, Object>> fetchRideHistory(@RequestParam Long driverId){
		Map<String, Object> response = new HashMap<>();
		
		List<BookingHistoryModel> history = service.fetchRideHistory(driverId);
		
		if(history == null) {
			response.put("message","No riding history for this id");
			response.put("status",HttpStatus.NOT_FOUND);
		}
		
		else {
			response.put("booking", history);
			response.put("status",HttpStatus.OK);
		}
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
