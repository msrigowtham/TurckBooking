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
public class VehicleModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicleId;
	private Long driverId;
	private String plateNo;
	private String driverLicense;
	private int vechicleCapacity;
	private int vechicleWidth;
	private int vechicleLength;
	private String vehicleType;
	private int minimumCharge;
	private int chargePerKm;
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Long getDriverId() {
		return driverId;
	}
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getDriverLicense() {
		return driverLicense;
	}
	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}
	public int getVechicleCapacity() {
		return vechicleCapacity;
	}
	public void setVechicleCapacity(int vechicleCapacity) {
		this.vechicleCapacity = vechicleCapacity;
	}
	public int getVechicleWidth() {
		return vechicleWidth;
	}
	public void setVechicleWidth(int vechicleWidth) {
		this.vechicleWidth = vechicleWidth;
	}
	public int getVechicleLength() {
		return vechicleLength;
	}
	public void setVechicleLength(int vechicleLength) {
		this.vechicleLength = vechicleLength;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public int getMinimumCharge() {
		return minimumCharge;
	}
	public void setMinimumCharge(int minimumCharge) {
		this.minimumCharge = minimumCharge;
	}
	public int getChargePerKm() {
		return chargePerKm;
	}
	public void setChargePerKm(int chargePerKm) {
		this.chargePerKm = chargePerKm;
	}
	public VehicleModel(Long vehicleId, Long driverId, String plateNo, String driverLicense, int vechicleCapacity,
			int vechicleWidth, int vechicleLength, String vehicleType, int minimumCharge, int chargePerKm) {
		super();
		this.vehicleId = vehicleId;
		this.driverId = driverId;
		this.plateNo = plateNo;
		this.driverLicense = driverLicense;
		this.vechicleCapacity = vechicleCapacity;
		this.vechicleWidth = vechicleWidth;
		this.vechicleLength = vechicleLength;
		this.vehicleType = vehicleType;
		this.minimumCharge = minimumCharge;
		this.chargePerKm = chargePerKm;
	}
	public VehicleModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
