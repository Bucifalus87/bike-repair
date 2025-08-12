package com.mobilebikerepair.bikerepair.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RepairRequestUpdateDTO {
    private String status;          // e.g., ASSIGNED, IN_PROGRESS, DONE
    private Long technicianId;
}

