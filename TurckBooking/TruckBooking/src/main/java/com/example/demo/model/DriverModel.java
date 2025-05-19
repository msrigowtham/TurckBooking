package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class DriverModel {
	
	@Id
	private Long id;
	private String fname;
	private String lname;
	private String DOB;
	private String email;
	private String password;
	private String role;
	private boolean available;
	private String date;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public DriverModel(Long id, String fname, String lname, String dOB, String email, String password, String role,
			boolean available, String date) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		DOB = dOB;
		this.email = email;
		this.password = password;
		this.role = role;
		this.available = available;
		this.date = date;
	}
	public DriverModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
