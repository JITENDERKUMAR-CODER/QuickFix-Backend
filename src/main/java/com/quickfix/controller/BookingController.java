package com.quickfix.controller;

import com.quickfix.dto.BookingRequest;
import com.quickfix.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @PostMapping
    public String createBooking(@RequestBody BookingRequest request) {
        System.out.println(request);

        System.out.println("===== Booking Controller Hit =====");


        return bookingService.createBooking(request);
    }

}