package com.example.demo.model;

import jakarta.persistence.Entity;
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
public class IdCreate {
	
	@Id
	private int id;
	private Long userId;
	private Long driverId;
	private Long bookingId;
	private Long adminId;
	
	
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getDriverId() {
		return driverId;
	}
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public IdCreate(int id, Long userId, Long driverId, Long bookingId,Long adminId) {
		super();
		this.id = id;
		this.userId = userId;
		this.driverId = driverId;
		this.bookingId = bookingId;
		this.adminId=adminId;
	}
	public IdCreate() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
