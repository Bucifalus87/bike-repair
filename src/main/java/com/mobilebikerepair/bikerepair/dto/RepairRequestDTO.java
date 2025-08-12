package com.mobilebikerepair.bikerepair.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairRequestDTO {

    private Long id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long customerId;
    private String customerName;

    private Long technicianId;
    private String technicianName;

    private Long repairTypeId;
    private String repairTypeName;
}
