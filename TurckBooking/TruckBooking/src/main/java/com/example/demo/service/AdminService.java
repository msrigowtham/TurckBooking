package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.model.AdminModel;
import com.example.demo.model.DriverModel;
import com.example.demo.model.IdCreate;
import com.example.demo.model.UsersModel;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.DriverRepository;
import com.example.demo.repository.IdCreateRepository;
import com.example.demo.repository.UserRepository;

import io.swagger.v3.oas.annotations.servers.Server;

@Server
public class AdminService {
	
	@Autowired
	public  AdminRepository  Adrepo;
	
	@Autowired
	public UserRepository  Userrepo;
	
	@Autowired
	public IdCreateRepository idrepo;
	
	@Autowired
	public DriverRepository  Driverrepo;



                 
//  User Details    
	
	public UsersModel getAdUser(long id) {
	   
		UsersModel User=Userrepo.getById(id);
		
		if(User!=null) {
			return null;
		}
		
		return User;
	}
	
	public List<UsersModel> getAllUser() {
		
		List<UsersModel> getAll= new ArrayList<>();
		      
		             getAll=Userrepo.findAll();
		             
		             if(getAll==null) {
		            	 return null;
		             }
		             
		             return getAll;
	}
	
	
	 public UsersModel updateUser(Long id, UsersModel updatedUser) {
	       Optional<UsersModel>obj= Userrepo.findById(id);
	        
	        if (obj.isPresent()) {
	        	UsersModel user= obj.get();
	        	
	        	user.setDOB(updatedUser.getDOB());
	        	user.setFname(updatedUser.getFname());
	        	user.setLname(updatedUser.getLname());
	        	user.setEmail(updatedUser.getEmail());
	        	user.setPassword(updatedUser.getPassword());
	        	user.setRole(updatedUser.getRole());
	        	return Userrepo.save(user);
	            } else {
	            throw new RuntimeException("User not found with id: " + id);
	            // throw new RuntimeException("User not found with id: " + id);
	        }
	             
	        }
	 
	 
		public UsersModel getdateuser(String date) {
			
			String Date =date;
			List<UsersModel> AllUserLisd= Userrepo.findBydate();
			  
			String ch =AllUserLisd.toString();
			if(Date.equals(ch.charAt(0))||Date.equals(ch.charAt(1))){
				 
				return Userrepo.getByDate(ch.charAt(0));
				
			}else {
				return AllUserLisd(date);
			}
			
		          
			
			
			
		}


//        Admin Driver
	 
	 
	public DriverModel getDriverId(Long id) {
		    
		  DriverModel getId =Driverrepo.getById(id);
		
		if(getId==null) {
			
			return null;
		}
		
		return getId;
	}

	public List<DriverModel> alldriverGet() {
		
		List<DriverModel> GetAllDriver=Driverrepo.findAll();
		
		return GetAllDriver;
	}

	public DriverModel updatedriber(Long id, DriverModel updateDriver) {
		
		Optional<DriverModel> Update=Driverrepo.findById(id);
		
		if(Update.isPresent()) {
			DriverModel updateDriverid = Update.get();
			
			updateDriverid.setFname(updateDriver.getFname());
			updateDriverid.setLname(updateDriver.getLname());
			updateDriverid.setDOB(updateDriver.getDOB());
			updateDriverid.setEmail(updateDriver.getEmail());
			updateDriverid.setPassword(updateDriver.getPassword());
			updateDriverid.setRole(updateDriver.getRole());
        	return Driverrepo.save(updateDriverid);
        } else {
        throw new RuntimeException("User not found with id: " + id);
         
    }
        	}	
	
	
	
	
	    
	       




















}
	
	
	
	
	
	
	
	
	
	
	


