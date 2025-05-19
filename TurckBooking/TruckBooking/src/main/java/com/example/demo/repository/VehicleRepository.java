package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.VehicleModel;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, Long> {

	VehicleModel getByDriverId(Long driverId);

}
