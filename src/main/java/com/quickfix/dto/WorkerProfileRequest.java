package com.quickfix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerProfileRequest {

    private Integer experience;

    private String bio;

    private String city;

    private BigDecimal hourlyRate;

    private Long categoryId;

}