package com.mobilebikerepair.bikerepair.mapper;

import com.mobilebikerepair.bikerepair.dto.RepairRequestDTO;
import com.mobilebikerepair.bikerepair.model.RepairRequest;
import org.springframework.stereotype.Component;

@Component
public class RepairRequestMapper {

    public RepairRequestDTO toDto(RepairRequest entity) {
        if (entity == null) {
            return null;
        }
        return new RepairRequestDTO(
                entity.getId(),
                entity.getDescription(),
                entity.getState(),
                entity.getCustomer() != null ? entity.getCustomer().getId() : null
        );
    }

    public RepairRequest toEntity(RepairRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        RepairRequest entity = new RepairRequest();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setState(dto.getState());
        // customer is set in service layer
        return entity;
    }
}
