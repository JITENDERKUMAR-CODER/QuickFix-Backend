package com.quickfix.controller;

import com.quickfix.dto.BookingRequest;
import com.quickfix.entity.Booking;
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
}