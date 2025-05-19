//package com.example.demo;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import com.example.demo.model.IdCreate;
//import com.example.demo.repository.IdCreateRepository;
//
//public class PasswordHasher {
//	
//	@Autowired
//	private static IdCreateRepository idCreateRepo;
//	
//	public static void main(String[] args) {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
//		String pass = "admin123";
//		String hashPassword = passwordEncoder.encode(pass);
//		System.out.println(hashPassword+":");
//		
//		LocalDateTime date = LocalDateTime.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//		String formattedDateTime = date.format(formatter);
//		
//		char[] ch = formattedDateTime.toCharArray();
//		IdCreate allId = idCreateRepo.getById(1);
//		String endIdconvert = Long.toString(allId.getDriverId());
//		
//		String idCreate = "111"+ch[3]+ch[4]+ch[8]+ch[9]+endIdconvert;
//	    allId.setDriverId(allId.getDriverId()+1);
//		int idConvert = Integer.parseInt(idCreate);
//		
//	System.out.println(idConvert);
//	}
//
//}
