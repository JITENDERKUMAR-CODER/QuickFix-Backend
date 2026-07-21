package com.quickfix.repository;

import com.quickfix.entity.Booking;
import com.quickfix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository
        extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomer(User customer);


}
