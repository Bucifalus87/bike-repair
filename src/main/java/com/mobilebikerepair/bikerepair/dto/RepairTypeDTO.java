package com.mobilebikerepair.bikerepair.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairTypeDTO {

    private Long id;
    private String name;
    private String description;
}

