package com.mobilebikerepair.bikerepair.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RepairRequestResponseDTO {
    private Long id;
    private String description;
    private String location;
    private String status;

    private String customerName;
    private String technicianName;
    private String repairTypeName;
}

