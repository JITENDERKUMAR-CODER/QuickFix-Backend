package com.quickfix.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BookingRequest {

    private  Long serviceId ;
    private Long workerId;
    private LocalDateTime bookingDateTime;

    private String address;
}
