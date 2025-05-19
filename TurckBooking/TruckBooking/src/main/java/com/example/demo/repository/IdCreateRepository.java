package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.IdCreate;

@Repository
public interface IdCreateRepository extends JpaRepository<IdCreate, Integer> {
	
	IdCreate getById(int id);

}
