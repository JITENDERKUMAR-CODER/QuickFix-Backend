package com.quickfix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "categories")
public class Category {

@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
      private Long id;
      @Column(unique = true,nullable = false)
      private String  name;
      private String description;
      @Column(nullable = false)
      private String icon;
      private Boolean active;
      private   LocalDateTime createdAt;
      private LocalDateTime updatedAt;
}
