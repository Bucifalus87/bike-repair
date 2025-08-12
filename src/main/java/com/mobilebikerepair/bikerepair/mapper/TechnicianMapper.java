package com.mobilebikerepair.bikerepair.mapper;

import com.mobilebikerepair.bikerepair.dto.TechnicianDTO;
import com.mobilebikerepair.bikerepair.model.Technician;

public class TechnicianMapper {

    public static TechnicianDTO toDTO(Technician technician) {
        if (technician == null) {
            return null;
        }
        return TechnicianDTO.builder()
                .id(technician.getId())
                .name(technician.getName())
                .email(technician.getUser() != null ? technician.getUser().getUsername() : null) // adjust if User has email
                .build();
    }

    public static Technician toEntity(TechnicianDTO dto) {
        if (dto == null) {
            return null;
        }
        Technician technician = new Technician();
        technician.setId(dto.getId());
        technician.setName(dto.getName());
        // email is handled by User entity
        return technician;
    }
}
