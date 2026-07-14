package com.quickfix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@Table(name = "services")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    @Column(nullable = false)
   private String  name;
   @Column(length = 1000)
   private String description;

   @Column(nullable = false)
   private BigDecimal price;

   private Integer estimatedDuration;

   private Boolean  active;

   private LocalDateTime createdAt;

   private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
