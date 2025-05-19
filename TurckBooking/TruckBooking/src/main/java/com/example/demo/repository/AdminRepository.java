package com.example.demo.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.AdminModel;
import com.example.demo.model.UsersModel;

@Repository
public interface AdminRepository {

	AdminModel save(UsersModel existingUser);

	UsersModel getById(Long id);

	UsersModel findById(Long id);



}
