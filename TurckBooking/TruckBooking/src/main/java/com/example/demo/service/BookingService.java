//package com.example.demo.service;
//
//	import com.example.demo.model.BookingHistoryModel;
//import com.example.demo.repository.BookingHistoryRepository;
//import com.itextpdf.kernel.pdf.*;
//	import com.itextpdf.layout.Document;
//	import com.itextpdf.layout.element.Paragraph;
//	import com.itextpdf.layout.element.Table;
//	import com.itextpdf.layout.element.Cell;
//	import org.springframework.beans.factory.annotation.Autowired;
//	import org.springframework.stereotype.Service;
//	import java.io.ByteArrayOutputStream;
//	import java.util.List;
//
//	@Service
//	public class BookingService {
//
//	    @Autowired
//	    private BookingHistoryRepository bookingRepo;
//
//	    public byte[] generateBookingHistoryPdf(Long userId) {
//	        List<BookingHistoryModel> bookings = bookingRepo.findById(userId);
//
//	        if (bookings.isEmpty()) {
//	            return null;  // No bookings found
//	        }
//
//	        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
//	            PdfWriter writer = new PdfWriter(outputStream);
//	            PdfDocument pdf = new PdfDocument(writer);
//	            Document document = new Document(pdf);
//
//	            document.add(new Paragraph("Booking History").setBold().setFontSize(18));
//
//	            // Create a table with headers
//	            Table table = new Table(16);
//	            table.addCell(new Cell().add("Booking ID"));
//	            table.addCell(new Cell().add("Pickup"));
//	            table.addCell(new Cell().add("Drop"));
//	            table.addCell(new Cell().add("Charge"));
//	            table.addCell(new Cell().add("Date"));
//
//	            for (BookingHistoryModel booking : bookings) {
//	                table.addCell(new Cell().add(booking.getBookingId().toString()));
//	                table.addCell(new Cell().add(booking.getPickUpAddress()));
//	                table.addCell(new Cell().add(booking.getDropAddress()));
//	                table.addCell(new Cell().add("₹" + booking.getBookingCharge()));
//	                table.addCell(new Cell().add(booking.getDate()));
//	            }
//
//	            document.add(table);
//	            document.close();
//
//	            return outputStream.toByteArray();
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            return null;
//	        }
//	    }
//	}
//
//
//
