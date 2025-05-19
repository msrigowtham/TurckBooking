package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.model.BookingHistoryModel;
import com.example.demo.model.BookingModel;
import com.example.demo.model.DriverModel;
import com.example.demo.model.IdCreate;
import com.example.demo.model.LocationModel;
import com.example.demo.model.UsersModel;
import com.example.demo.model.VehicleModel;
import com.example.demo.repository.BookingHistoryRepository;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.DriverRepository;
import com.example.demo.repository.IdCreateRepository;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VehicleRepository;

@Service
public class DriverService {
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
	
	@Autowired
	private DriverRepository repo;
	
	@Autowired
	private LocationRepository locoRepo;
	
	@Autowired
	private BookingRepository bookrepo;
	
	@Autowired
	private VehicleRepository vehiclerepo;
	
	@Autowired
	private BookingHistoryRepository historyrepo;
	
	@Autowired
	private IdCreateRepository idCreateRepo;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private JavaMailSender mail;
	
	
	
	
	
	
	
	
	public VehicleModel updateDriverVehicleDetails( VehicleModel updatedVehicle) {
	   
	    VehicleModel existingVehicle =vehiclerepo.save(updatedVehicle);
	    
	    
	    existingVehicle.setVechicleCapacity(updatedVehicle.getVechicleCapacity());
	    existingVehicle.setVechicleWidth(updatedVehicle.getVechicleWidth());
	    existingVehicle.setVechicleLength(updatedVehicle.getVechicleLength());
	    existingVehicle.setMinimumCharge(updatedVehicle.getMinimumCharge());
	    existingVehicle.setChargePerKm(updatedVehicle.getChargePerKm());

	    return existingVehicle;
	}


	
	public DriverModel updateDriver(Long driverId, DriverModel updatedDriver) {
	    DriverModel existingDriver = repo.findById(driverId).orElse(null);
	    
	    if (existingDriver == null) {
	        return null;
	    }

	    existingDriver.setFname(updatedDriver.getFname());
	    existingDriver.setLname(updatedDriver.getLname());
	    existingDriver.setDOB(updatedDriver.getDOB());
	    existingDriver.setEmail(updatedDriver.getEmail());
	    existingDriver.setPassword(updatedDriver.getPassword());
	   

	    return repo.save(existingDriver);
	}

	
	
	
	
	
	

	public DriverModel saveDriver(DriverModel driver) {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String formattedDateTime = date.format(formatter);
		char[] ch = formattedDateTime.toCharArray();
		IdCreate allId = idCreateRepo.getById(1);
		String endIdconvert = Long.toString(allId.getDriverId());
		String idCreate = "111"+ch[3]+ch[4]+ch[8]+ch[9]+endIdconvert;
		allId.setDriverId(allId.getDriverId()+1);
		idCreateRepo.save(allId);
		Long idConvert = Long.parseLong(idCreate);
		
		driver.setId(idConvert);
		driver.setDate(formattedDateTime);
		driver.setPassword(passwordEncoder.encode(driver.getPassword()));
		
		
		 return repo.save(driver);
		
	}

	public DriverModel getDriver(String driverMail) {
		
		DriverModel driver = repo.getByEmail(driverMail);
		if(driver == null) {
			return null;
		}
		return driver;
		
	}

	public DriverModel changeAvailability(String driverMail) {
		DriverModel driver = repo.getByEmail(driverMail);
		
		if(driver == null) {
			return null;
		}
		
		if(driver.isAvailable()) {
			driver.setAvailable(false);
		}
		else {
			driver.setAvailable(true);
		}
		
		repo.save(driver);
		
		return driver;
	}

	public String updateLocation(LocationModel location) {
		
		String message = "";
		
		DriverModel driver = repo.getById(location.getDriverId());
		
		if(driver == null || driver.isAvailable()) {
			message = null;
		}
		
		LocationModel locationmodel = locoRepo.getByDriverId(location.getDriverId());
		
		if(locationmodel != null && driver.isAvailable()) {
			locationmodel.setLatitude(location.getLatitude());
			locationmodel.setLongitude(location.getLongitude());
			locoRepo.save(locationmodel);
		}
		
		else if(locationmodel != null && !driver.isAvailable()) {
			locoRepo.delete(locationmodel);
		}
		else {
			locoRepo.save(location);
			message = "Location updated successfully";
		}
		
		return message;
	}

	public BookingModel fetchBooking(Long driverId) {
		
		BookingModel booking = bookrepo.getByDriverId(driverId);
		
		if(booking == null) {
			return null;
		}
		
		
		return booking;
	}

	public BookingModel confirmBooking(BookingModel booking) {
		BookingModel bookingResponse = bookrepo.getByDriverId(booking.getDriverId());
		
		UsersModel user = userrepo.getById(booking.getUserId());
		DriverModel driver = repo.getById(booking.getDriverId());
		
		if(bookingResponse == null) {
			return null;
		}
		
		bookingResponse.setBookingStatus(booking.getBookingStatus());
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(user.getEmail());
		msg.setSubject("Your Booking Status updated !");
		msg.setText("For Booking : /nPick up location : "+booking.getPickUpAddress()+"\nDrop location : "+booking.getDropAddress()+"\n"
				+ "Product : "+booking.getProductName()+"\nProduct weight : "+booking.getProductWeight()
				+"Booking Status : "+booking.getBookingStatus()+"\nBooking is "+booking.getBookingStatus()+" driver "+driver.getFname());
		mail.send(msg);
		
		return bookrepo.save(bookingResponse);
	}

	public VehicleModel saveVehicle(VehicleModel vehicle) {
		
		DriverModel driver = repo.getById(vehicle.getDriverId());
		if(driver == null) {
			return null;
		}
		
		if(vehicle.getVehicleType().equalsIgnoreCase("TYPEONE")) {
		vehicle.setMinimumCharge(150);
		
		vehicle.setChargePerKm(15);
	}
	
	else if(vehicle.getVehicleType().equalsIgnoreCase("TYPETWO")) {
	      vehicle.setMinimumCharge(180);
		
		vehicle.setChargePerKm(20);
	}
	
	else if(vehicle.getVehicleType().equalsIgnoreCase("TYPETHREE")) {
		vehicle.setMinimumCharge(220);
		
		vehicle.setChargePerKm(25);
	}
	
	else if(vehicle.getVehicleType().equalsIgnoreCase("TYPEFOUR")) {
		vehicle.setMinimumCharge(250);
		
		vehicle.setChargePerKm(30);
	}
		
		return vehiclerepo.save(vehicle);
	}

	public BookingModel endRide(Long bookingId) {
		BookingModel booking = bookrepo.getByBookingId(bookingId);
		
		if(booking == null) {
			return null;
		}
		
		BookingHistoryModel historyBook = historyrepo.getByBookingId(bookingId);
		
		if(historyBook == null) {
			historyBook.setBookingId(booking.getBookingId());
			historyBook.setBookingCharge(booking.getBookingCharge());
			historyBook.setBookingStatus("completed");
			historyBook.setDriverId(booking.getDriverId());
			historyBook.setDropAddress(booking.getDropAddress());
			historyBook.setDropLat(booking.getDropLat());
			historyBook.setDroplon(booking.getDroplon());
			historyBook.setPickUpAddress(booking.getPickUpAddress());
			historyBook.setPickUpLat(booking.getPickUpLat());
			historyBook.setPickUplon(booking.getPickUplon());
			historyBook.setProductLength(booking.getProductLength());
			historyBook.setProductName(booking.getProductName());
			historyBook.setProductWeight(booking.getProductWeight());
			historyBook.setProductWidth(booking.getProductWidth());
			historyBook.setUserId(booking.getUserId());
			historyrepo.save(historyBook);
		}
		return bookrepo.deleteByBookingId(bookingId);
	}

	public List<BookingHistoryModel> fetchRideHistory(Long driverId) {
		List<BookingHistoryModel> history = (List<BookingHistoryModel>) historyrepo.getByDriverId(driverId);
		
		if(history == null) {
			return null;
		}
		
		return history;
	}
}
