package com.mobilebikerepair.bikerepair.mapper;

import com.mobilebikerepair.bikerepair.dto.RepairTypeDTO;
import com.mobilebikerepair.bikerepair.model.RepairType;

public class RepairTypeMapper {

    public static RepairTypeDTO toDTO(RepairType repairType) {
        if (repairType == null) {
            return null;
        }
        return RepairTypeDTO.builder()
                .id(repairType.getId())
                .name(repairType.getName())
                .description(repairType.getDescription())
                .build();
    }

    public static RepairType toEntity(RepairTypeDTO dto) {
        if (dto == null) {
            return null;
        }
        RepairType repairType = new RepairType();
        repairType.setId(dto.getId());
        repairType.setName(dto.getName());
        repairType.setDescription(dto.getDescription());
        return repairType;
    }
}
