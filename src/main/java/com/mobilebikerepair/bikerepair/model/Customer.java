package com.mobilebikerepair.bikerepair.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // Not exposed via API (we use DTOs). Kept for domain integrity.
    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<RepairRequest> repairRequests = new ArrayList<>();
}
