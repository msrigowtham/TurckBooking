package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LocationModel;

@Repository
public interface LocationRepository extends JpaRepository<LocationModel, Integer> {
	
	LocationModel getByDriverId(Long driverId);
}
