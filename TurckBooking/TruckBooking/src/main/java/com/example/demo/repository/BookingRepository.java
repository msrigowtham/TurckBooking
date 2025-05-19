package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BookingModel;

@Repository
public interface BookingRepository extends JpaRepository<BookingModel, Integer>{
	
	BookingModel getByDriverId(Long driverId);
	
	BookingModel getByBookingId(Long bookingId);
	
	BookingModel deleteByBookingId(Long Id);
	
}
