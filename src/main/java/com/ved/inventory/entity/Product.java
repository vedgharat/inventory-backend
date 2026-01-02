package com.ved.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products") // Maps to a DB table named 'products'
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // Helps create objects easily
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(length = 1000)
    private String description;

    // Optional: Audit fields (created_at, etc.) can be added here later
}