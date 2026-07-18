package com.quickfix.dto;

import com.quickfix.entity.enums.BookingStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingResponse {
   private Long id;

   private String customerName;

   private String workerName;

   private String  serviceName;

   private LocalDateTime bookingDateTime;

   private String  address;

   private BookingStatus status;

}
