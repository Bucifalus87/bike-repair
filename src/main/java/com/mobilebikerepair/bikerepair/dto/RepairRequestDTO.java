package com.mobilebikerepair.bikerepair.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairRequestDTO {
    private Long id;
    private String description;
    private String state;
    private Long customerId;
}
