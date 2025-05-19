package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DriverModel;

@Repository
public interface DriverRepository extends JpaRepository<DriverModel, Long>{
	
	DriverModel getByEmail(String mail);
	
	DriverModel getById(Long id);
	

}
