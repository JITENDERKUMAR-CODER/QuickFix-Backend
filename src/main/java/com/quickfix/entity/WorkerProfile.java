package com.quickfix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "worker_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Yahan relationship aayega

    private Integer experience;

    @Column(length = 1000)
    private String bio;

    private String city;

    private BigDecimal hourlyRate;

    private Boolean verified;

    private Double rating;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}