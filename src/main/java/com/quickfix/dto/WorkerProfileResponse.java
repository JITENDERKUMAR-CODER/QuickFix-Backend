package com.quickfix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkerProfileResponse {

       private Long  id;

       private String fullName;
       private String email;
       private Integer experience;
       private String bio;
       private String  city;
       private BigDecimal hourlyRate;

       private Boolean  verified;
       private Double rating;
}
