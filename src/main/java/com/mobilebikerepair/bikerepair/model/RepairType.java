package com.mobilebikerepair.bikerepair.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RepairType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // e.g., Tire Change
    private String description; // e.g., Replace inner tube and refit tire
}