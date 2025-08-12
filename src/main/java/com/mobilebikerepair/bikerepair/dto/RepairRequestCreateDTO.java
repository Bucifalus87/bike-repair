package com.mobilebikerepair.bikerepair.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RepairRequestCreateDTO {
    private String description;
    private String location;
    private Long customerId;
    private Long repairTypeId;
}

