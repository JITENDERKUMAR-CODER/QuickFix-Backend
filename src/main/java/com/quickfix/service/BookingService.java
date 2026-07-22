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
    public List<Booking> getWorkerBookings() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        WorkerProfile worker = workerRepository.findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Worker Profile Not Found"));

        return bookingRepository.findByWorker(worker);
    }
    public Booking acceptBooking(Long bookingId) {

        WorkerProfile loggedInWorker = getLoggedInWorker();

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getWorker().getId().equals(loggedInWorker.getId())) {
            throw new RuntimeException("You are not authorized to accept this booking");
        }

        booking.setStatus(BookingStatus.ACCEPTED);

        return bookingRepository.save(booking);
    }
    public Booking rejectBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.REJECTED);

        return bookingRepository.save(booking);
    }
    public Booking startWork(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.IN_PROGRESS);

        return bookingRepository.save(booking);
    }
    public Booking completeWork(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.COMPLETED);

        return bookingRepository.save(booking);
    }
    private WorkerProfile getLoggedInWorker() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return workerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Worker Profile Not Found"));
    }
    public Booking cancelBooking(Long bookingId) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User customer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getCustomer().getId().equals(customer.getId())) {
            throw new RuntimeException("You can cancel only your own booking");
        }

        if (booking.getStatus() == BookingStatus.IN_PROGRESS ||
                booking.getStatus() == BookingStatus.COMPLETED) {

            throw new RuntimeException("Booking cannot be cancelled now");
        }

        booking.setStatus(BookingStatus.CANCELLED);

        return bookingRepository.save(booking);
    }


}
