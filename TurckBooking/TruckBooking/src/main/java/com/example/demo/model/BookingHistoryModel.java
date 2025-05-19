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
public class BookingHistoryModel {
	
	@Id
	private Long bookingId;
	private Long userId;
	private Long driverId;
	private String productName;
	private int productLength;
	private int productWidth;
	private int productWeight;
	private double pickUpLat;
	private double pickUplon;
	private String pickUpAddress;
	private double dropLat;
	private double droplon;
	private String dropAddress;
	private int bookingCharge;
	private String bookingStatus;
	private String date;
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductLength() {
		return productLength;
	}
	public void setProductLength(int productLength) {
		this.productLength = productLength;
	}
	public int getProductWidth() {
		return productWidth;
	}
	public void setProductWidth(int productWidth) {
		this.productWidth = productWidth;
	}
	public int getProductWeight() {
		return productWeight;
	}
	public void setProductWeight(int productWeight) {
		this.productWeight = productWeight;
	}
	public double getPickUpLat() {
		return pickUpLat;
	}
	public void setPickUpLat(double pickUpLat) {
		this.pickUpLat = pickUpLat;
	}
	public double getPickUplon() {
		return pickUplon;
	}
	public void setPickUplon(double pickUplon) {
		this.pickUplon = pickUplon;
	}
	public String getPickUpAddress() {
		return pickUpAddress;
	}
	public void setPickUpAddress(String pickUpAddress) {
		this.pickUpAddress = pickUpAddress;
	}
	public double getDropLat() {
		return dropLat;
	}
	public void setDropLat(double dropLat) {
		this.dropLat = dropLat;
	}
	public double getDroplon() {
		return droplon;
	}
	public void setDroplon(double droplon) {
		this.droplon = droplon;
	}
	public String getDropAddress() {
		return dropAddress;
	}
	public void setDropAddress(String dropAddress) {
		this.dropAddress = dropAddress;
	}
	public int getBookingCharge() {
		return bookingCharge;
	}
	public void setBookingCharge(int bookingCharge) {
		this.bookingCharge = bookingCharge;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public BookingHistoryModel(Long bookingId, Long userId, Long driverId, String productName, int productLength,
			int productWidth, int productWeight, double pickUpLat, double pickUplon, String pickUpAddress,
			double dropLat, double droplon, String dropAddress, int bookingCharge, String bookingStatus, String date) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.driverId = driverId;
		this.productName = productName;
		this.productLength = productLength;
		this.productWidth = productWidth;
		this.productWeight = productWeight;
		this.pickUpLat = pickUpLat;
		this.pickUplon = pickUplon;
		this.pickUpAddress = pickUpAddress;
		this.dropLat = dropLat;
		this.droplon = droplon;
		this.dropAddress = dropAddress;
		this.bookingCharge = bookingCharge;
		this.bookingStatus = bookingStatus;
		this.date = date;
	}
	public BookingHistoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
