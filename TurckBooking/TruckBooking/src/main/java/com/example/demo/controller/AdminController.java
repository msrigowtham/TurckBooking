package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AdminModel;
import com.example.demo.model.DriverModel;
import com.example.demo.model.UsersModel;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
     
//	@Autowired
//    private  AdminService adminService;
	
	@Autowired
    private  AdminService adminService;

    
 


    @GetMapping("/getuser")
    public ResponseEntity<Map<String, Object>> getUser(@RequestParam long id) {
    	
    	Map<String,Object>Response = new HashMap<>();
    	
    	if(id != 0) {
    		UsersModel User = adminService.getAdUser(id);
    		if(User ==null) {
    			Response.put("message", "User not fount");
    			Response.put("status", HttpStatus.NOT_FOUND);
    		}else {
    			Response.put("message", User);
    			Response.put("status",HttpStatus.OK );
    		}
    	}
    		
    		return new ResponseEntity<>(Response,HttpStatus.OK);
    	}
    	
    	
    
    

    
    @GetMapping("/getallusers")
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        Map<String, Object> Allresponse = new HashMap<>();
        List<UsersModel> users = adminService.getAllUser();
        if (users.isEmpty()) {
            Allresponse.put("message", "No users found");
            Allresponse.put("status", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(Allresponse, HttpStatus.NOT_FOUND);
        } else {
            Allresponse.put("message", "Users retrieved successfully");
            Allresponse.put("users", users);
            Allresponse.put("status", HttpStatus.OK);
            return new ResponseEntity<>(Allresponse, HttpStatus.OK);
        }
    }    
    
    
    
    @PutMapping("/update-user/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody UsersModel updatedUser) {
        Map<String, Object> response = new HashMap<>();
        try {
            UsersModel user = adminService.updateUser(id, updatedUser);
            response.put("message", "User updated successfully");
            response.put("user", user);
            response.put("status", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    
    
    @GetMapping("/date")
    public ResponseEntity<Map<String, Object>> getUser(@RequestParam String date) {
    	
    	Map<String,Object>Response = new HashMap<>();
    	
    	if(date!=null) {
    		UsersModel User = adminService.getdateuser(date);
    		if(User ==null) {
    			Response.put("message", "User not fount");
    			Response.put("status", HttpStatus.NOT_FOUND);
    		}else {
    			Response.put("message", User);
    			Response.put("status",HttpStatus.OK );
    		}
    	}
    		
    		return new ResponseEntity<>(Response,HttpStatus.OK);
    	}
    
    
    
    
    
    
//    Admin Driver Access
    
    
    @GetMapping("getdriver")
    public ResponseEntity<Map<String,Object>>  getDriverId(@ RequestParam Long id){
    	
    	Map<String,Object> Driver=new HashMap<>();
    	DriverModel getdriver =adminService.getDriverId(id);
    	      if(getdriver == null) {
    	    	  Driver.put("Message", "Driver not found");
    	    	  Driver.put("Status", HttpStatus.NOT_FOUND);
    	      }else {
    	    	  Driver.put("Message", getdriver);
    	    	  Driver.put("status", HttpStatus.OK);
    	      }
    	      
    	      return new ResponseEntity(Driver,HttpStatus.OK);	
    }
    
    
    @GetMapping("/alldriver")
    public ResponseEntity<Map<String,Object>> getAllDriver(){
    	Map<String,Object> getAllDriver = new HashMap<>();
    	
    	List<DriverModel> Alldriver =adminService.alldriverGet();
    	
    	if(Alldriver==null) {
    		
    		getAllDriver.put("Messege", "Driver NOt Fount");
    		getAllDriver.put("status", HttpStatus.NOT_FOUND);
    	}else {
    	     getAllDriver.put("message", Alldriver);
    	     getAllDriver.put("Status", HttpStatus.OK);
    	}
    	
    	return new ResponseEntity<>(getAllDriver,HttpStatus.OK);
    	
    }
    
    
    public ResponseEntity<Map<String,Object>> updateDriver(@PathVariable Long id,@RequestBody DriverModel UpdateDriver){
    	
    	Map<String,Object> Updateddriver = new HashMap<>();
    	
    	try {
    	      DriverModel DriverUpdate=adminService.updatedriber(id,UpdateDriver);
    	      Updateddriver.put("massege",DriverUpdate);
    	      Updateddriver.put("Status", HttpStatus.OK);
    	       return new ResponseEntity(Updateddriver,HttpStatus.OK);
    	}catch(RuntimeException e) {
    		
    		Updateddriver.put("massege",e.getMessage()); 
  	      Updateddriver.put("Status", HttpStatus.NOT_FOUND);
  	       return new ResponseEntity(Updateddriver,HttpStatus.NOT_FOUND);
    		
    	}
 
    }
    
    
    
    
    
    
    
}
