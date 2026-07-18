package com.quickfix.repository;

import com.quickfix.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository
        extends JpaRepository<Booking, Long> {

}
