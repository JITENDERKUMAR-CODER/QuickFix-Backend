package com.quickfix.service;

import com.quickfix.dto.BookingRequest;
import com.quickfix.entity.Booking;
import com.quickfix.entity.User;
import com.quickfix.entity.WorkerProfile;
import com.quickfix.entity.enums.BookingStatus;
import com.quickfix.repository.BookingRepository;
import com.quickfix.repository.ServiceRepository;
import com.quickfix.repository.UserRepository;
import com.quickfix.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.quickfix.entity.Service;

import java.time.LocalDateTime;
import java.util.List;

@org.springframework.stereotype.Service
public class BookingService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public String createBooking(BookingRequest request) {
    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();
        System.out.println("Logged in email: " + email);

    User customer = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User Not Found"));
    WorkerProfile worker = workerRepository.findById(request.getWorkerId())
            .orElseThrow(() ->
                    new RuntimeException("Worker Not Found"));
    Service service = serviceRepository.findById(request.getServiceId())
            .orElseThrow(() ->
                    new RuntimeException("Service Not Found"));

    Booking booking = new Booking();
    booking.setCustomer(customer);
    booking.setWorker(worker);
    booking.setService(service);

    booking.setAddress(request.getAddress());
    booking.setBookingDateTime(request.getBookingDateTime());

    booking.setStatus(BookingStatus.PENDING);
    booking.setCreatedAt(LocalDateTime.now());
    booking.setUpdatedAt(LocalDateTime.now());
        if (request.getBookingDateTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Booking date must be in the future");
        }
        if (!worker.getCategory().getId().equals(service.getCategory().getId())) {
            throw new RuntimeException("Selected worker cannot provide this service");
        }

    bookingRepository.save(booking);

        return "Booking Created Successfully";
}
    public List<Booking> getMyBookings() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User customer = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        return bookingRepository.findByCustomer(customer);
    }

}
