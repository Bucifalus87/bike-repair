package com.mobilebikerepair.bikerepair.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



@Entity
@Getter
@Setter
public class RepairRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String location;
    private String status; // e.g., NEW, ASSIGNED, IN_PROGRESS, DONE

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Technician technician;

    @ManyToOne
    private RepairType repairType;

    @OneToMany(mappedBy = "repairRequest", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
