package com.quickfix.repository;

import com.quickfix.entity.Booking;
import com.quickfix.entity.User;
import com.quickfix.entity.WorkerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository
        extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomer(User customer);
    List<Booking> findByWorker(WorkerProfile worker);

    Optional<Booking> findById(Long id);


}
