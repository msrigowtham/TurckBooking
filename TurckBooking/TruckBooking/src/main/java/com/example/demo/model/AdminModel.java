package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AdminModel {
	
	
	
	@Id
	private Long id;
	private String fname;
	private String lname;
	private String DOB;
	private String email;
	private String password;
	private String role;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public AdminModel(Long id, String fname, String lname, String dOB, String email, String password, String role,
			String date) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		DOB = dOB;
		this.email = email;
		this.password = password;
		this.role = role;
		this.date = date;
	}
	public AdminModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
