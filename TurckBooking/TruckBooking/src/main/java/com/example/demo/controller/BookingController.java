//package com.example.demo.controller;
//
//	
//    import org.springframework.beans.factory.annotation.Autowired;
//    import org.springframework.http.HttpHeaders;
//	import org.springframework.http.HttpStatus;
//	import org.springframework.http.ResponseEntity;
//	import org.springframework.web.bind.annotation.*;


//	  <dependency>
//   <groupId>com.itextpdf</groupId>
//    <artifactId>itext7-core</artifactId>
//   <version>7.1.16</version>
//    </dependency>

//
//import com.example.demo.service.BookingService;
//
//	@RestController
//	@RequestMapping("/booking")
//	public class BookingController {
//
//	    @Autowired
//	    private BookingService bookingService;
//
//	    @GetMapping("/history/pdf/{userId}")
//	    public ResponseEntity<byte[]> downloadBookingHistoryPdf(@PathVariable Long userId) {
//	        byte[] pdfData = bookingService.generateBookingHistoryPdf(userId);
//
//	        if (pdfData == null) {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//	                    .body("No booking history found for user.".getBytes());
//	        }
//
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.add("Content-Disposition", "attachment; filename=Booking_History.pdf");
//
//	        return ResponseEntity.ok()
//	                .headers(headers)
//	                .body(pdfData);
//	    }
//	}
//
//
//
