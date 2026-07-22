package com.quickfix.controller;

import com.quickfix.dto.BookingRequest;
import com.quickfix.entity.Booking;
import com.quickfix.entity.enums.BookingStatus;
import com.quickfix.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public String createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }

    @GetMapping("/my")
    public List<Booking> getMyBookings() {
        return bookingService.getMyBookings();
    }
    @GetMapping("/worker")
    public List<Booking> getWorkerBookings() {
        return bookingService.getWorkerBookings();
    }
    @PutMapping("/{id}/accept")
    public Booking acceptBooking(@PathVariable Long id) {
        return bookingService.acceptBooking(id);
    }
    @PutMapping("/{id}/reject")
    public Booking rejectBooking(@PathVariable Long id) {

        return bookingService.rejectBooking(id);
    }
    @PutMapping("/{id}/start")
    public Booking startWork(@PathVariable Long id) {
        System.out.println("Start endpoint hit");
        return bookingService.startWork(id);
    }
    @PutMapping("/{id}/complete")
    public Booking completeWork(@PathVariable Long id) {
        return bookingService.completeWork(id);
    }
    @PutMapping("/{id}/cancel")
    public Booking cancelBooking(@PathVariable Long id) {
        return bookingService.cancelBooking(id);
    }
}