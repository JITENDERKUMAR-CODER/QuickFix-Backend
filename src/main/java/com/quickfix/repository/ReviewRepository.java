package com.quickfix.repository;

import com.quickfix.entity.Booking;
import com.quickfix.entity.Review;
import com.quickfix.entity.WorkerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByWorker(WorkerProfile worker);

    boolean existsByBooking(Booking booking);

}
