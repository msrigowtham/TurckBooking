package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.UsersModel;

@Repository
public interface UserRepository extends JpaRepository<UsersModel, Long> {
	
	UsersModel getByEmail(String userMail);
	
	UsersModel getById(Long id);
	
	UsersModel getByDate(String date);

	List<UsersModel> findBydate();

	UsersModel getByDate(char charAt);

	

}
