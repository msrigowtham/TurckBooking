package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BookingHistoryModel;

@Repository
public interface BookingHistoryRepository extends JpaRepository<BookingHistoryModel, Long> {

	BookingHistoryModel getByUserId(Long userId);
	
	BookingHistoryModel getByDriverId(Long driverId);
	
	BookingHistoryModel getByBookingId(Long bookingId);
}
