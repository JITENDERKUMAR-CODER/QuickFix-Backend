package com.quickfix.service;

import com.quickfix.dto.ReviewRequest;
import com.quickfix.entity.Booking;
import com.quickfix.entity.Review;
import com.quickfix.entity.User;
import com.quickfix.entity.enums.BookingStatus;
import com.quickfix.repository.BookingRepository;
import com.quickfix.repository.ReviewRepository;
import com.quickfix.repository.UserRepository;
import com.quickfix.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    public String addReview(ReviewRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User customer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getCustomer().getId().equals(customer.getId())) {
            throw new RuntimeException("You can review only your own booking");
        }

        if (booking.getStatus() != BookingStatus.COMPLETED) {
            throw new RuntimeException("Review allowed only after work completion");
        }

        if (reviewRepository.existsByBooking(booking)) {
            throw new RuntimeException("Review already submitted");
        }

        Review review = new Review();

        review.setBooking(booking);
        review.setCustomer(customer);
        review.setWorker(booking.getWorker());
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreatedAt(LocalDateTime.now());

        reviewRepository.save(review);

        return "Review Added Successfully";
    }
    final ffrff
}
