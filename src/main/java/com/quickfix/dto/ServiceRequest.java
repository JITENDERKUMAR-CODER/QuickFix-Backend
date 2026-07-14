package com.quickfix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {
    private String name;

    private String description;

    private BigDecimal price;

    private Integer estimatedDuration;

    private Long categoryId;
}
