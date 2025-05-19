package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.BookingModel;
import com.example.demo.model.DriverModel;
import com.example.demo.model.IdCreate;
import com.example.demo.model.LocationModel;
import com.example.demo.model.UsersModel;
import com.example.demo.model.VehicleModel;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.DriverRepository;
import com.example.demo.repository.IdCreateRepository;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VehicleRepository;

@Service
public class UserService {
	
	private static final double EARTH_RADIUS = 6371.0; 
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private DriverRepository driverRepo;
	
	@Autowired
	private LocationRepository locorepo;
	
	@Autowired
	private BookingRepository bookrepo;
	
	@Autowired
	private VehicleRepository vehiclerepo;
	
	@Autowired
	private IdCreateRepository idCreateRepo;
	
	@Autowired
	public JavaMailSender mail;
	
	
	public UsersModel updateUser(Long userId, UsersModel updatedUser) {
	    UsersModel existingUser = repo.findById(userId).orElse(null);
	    
	    if (existingUser == null) {
	        return null;
	    }
	    
	    existingUser.setFname(updatedUser.getFname());
	    existingUser.setLname(updatedUser.getLname());
	    existingUser.setEmail(updatedUser.getEmail());
	   
	    
	    if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
	        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
	    }
	    
	    return repo.save(existingUser);
	}

	


	public UsersModel saveUsers(UsersModel user) {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String formattedDateTime = date.format(formatter);
		char[] ch = formattedDateTime.toCharArray();
		IdCreate allId = idCreateRepo.getById(1);
		String endIdconvert = Long.toString(allId.getUserId());
		String idCreate = "222"+ch[3]+ch[4]+ch[8]+ch[9]+endIdconvert;
		allId.setUserId(allId.getUserId()+1);
		idCreateRepo.save(allId);
		Long idConvert = Long.parseLong(idCreate);
		
		user.setId(idConvert);
		user.setDate(formattedDateTime);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}

	public UsersModel getUser(String userMail) {
		
		UsersModel user = repo.getByEmail(userMail);
		
		if(user == null) {
			return null;
		}
		
		return user;
	}

	public List<BookingModel> getVehicle(BookingModel booking) {
		List<LocationModel> location = locorepo.findAll();
		
		List<DriverModel> allDriver = new ArrayList<>();
		
		VehicleModel vehicle = vehiclerepo.getByDriverId(booking.getDriverId());
		
		location.stream()
        .filter(loc -> calculateDistance(booking.getPickUpLat(),booking.getPickUplon(),loc.getLatitude(),loc.getLongitude()) <= 5)
        .collect(Collectors.toList());
		
		for(LocationModel findDriver : location) {
			DriverModel driver = driverRepo.getById(findDriver.getDriverId());
			if(driver != null) {
				allDriver.add(driver);
			}}
		for(DriverModel driverList : allDriver) {
			
			if(vehicle.getVechicleCapacity() < booking.getProductWeight() || vehicle.getVechicleWidth() < booking.getProductWidth() || 
					vehicle.getVechicleLength() < booking.getProductLength()) {
				allDriver.remove(allDriver.indexOf(driverList));
			}
		}
		List<BookingModel> bookingResponse = new ArrayList<>();
		
		for(DriverModel chargeCal : allDriver) {
			DriverModel driver = driverRepo.getById(chargeCal.getId());
			LocationModel driverLoc = locorepo.getByDriverId(driver.getId());
			
			if(calculateDistance(booking.getPickUpLat(),booking.getPickUplon(),driverLoc.getLatitude(),driverLoc.getLongitude()) < 3) {
				booking.setBookingCharge(vehicle.getMinimumCharge());
			}
			
			else {
				int charge = (int) (calculateDistance(booking.getPickUpLat(),booking.getPickUplon(),driverLoc.getLatitude(),driverLoc.getLongitude()) * vehicle.getChargePerKm());
				booking.setBookingCharge(charge);
			}
			
			bookingResponse.add(booking);
		}
		return bookingResponse;
	}

	public BookingModel requestVehicle(BookingModel booking) {
		
		DriverModel driver = driverRepo.getById(booking.getDriverId());
		UsersModel user = repo.getById(booking.getUserId());
		
		if(driver == null || user == null) {
			return null;
		}
		

		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String formattedDateTime = date.format(formatter);
		char[] ch = formattedDateTime.toCharArray();
		IdCreate allId = idCreateRepo.getById(1);
		String endIdconvert = Long.toString(allId.getBookingId());
		Long userId = allId.getUserId();
		Long driverId = allId.getDriverId();
		String userIdConvert = userId.toString();
		String driverIdConvert = driverId.toString();
		String idCreate = ""+userIdConvert.substring(7)+driverIdConvert.substring(7)+ch[3]+ch[4]+ch[8]+ch[9]+endIdconvert;
		allId.setBookingId(allId.getBookingId()+1);
		idCreateRepo.save(allId);
		Long idConvert = Long.parseLong(idCreate);
		
		booking.setBookingId(idConvert);
		booking.setDate(formattedDateTime);
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(driver.getEmail());
		msg.setSubject("Booking Request From User : "+user.getFname());
		msg.setText("Pick up location : "+booking.getPickUpAddress()+"\nDrop location : "+booking.getDropAddress()+"\n"
				+ "Product : "+booking.getProductName()+"\nProduct weight : "+booking.getProductWeight()
				+"\nPlease Confirm the Booking through HAULXPRESS website");
		mail.send(msg);
		
		return bookrepo.save(booking);
	}
	
	
	
	
	
	
	
	
	

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convert degrees to radians
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        // Haversine formula
        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance in kilometers
        return EARTH_RADIUS * c;
    }
	
	

}
