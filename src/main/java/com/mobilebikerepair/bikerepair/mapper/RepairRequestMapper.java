package com.mobilebikerepair.bikerepair.mapper;

import com.mobilebikerepair.bikerepair.dto.RepairRequestDTO;
import com.mobilebikerepair.bikerepair.model.Customer;
import com.mobilebikerepair.bikerepair.model.RepairRequest;
import com.mobilebikerepair.bikerepair.model.RepairType;
import com.mobilebikerepair.bikerepair.model.Technician;

public class RepairRequestMapper {

    public static RepairRequestDTO toDTO(RepairRequest repairRequest) {
        if (repairRequest == null) {
            return null;
        }
        return RepairRequestDTO.builder()
                .id(repairRequest.getId())
                .description(repairRequest.getDescription())
                .status(repairRequest.getStatus())
                .customerId(repairRequest.getCustomer() != null ? repairRequest.getCustomer().getId() : null)
                .customerName(repairRequest.getCustomer() != null ? repairRequest.getCustomer().getName() : null)
                .technicianId(repairRequest.getTechnician() != null ? repairRequest.getTechnician().getId() : null)
                .technicianName(repairRequest.getTechnician() != null ? repairRequest.getTechnician().getName() : null)
                .repairTypeId(repairRequest.getRepairType() != null ? repairRequest.getRepairType().getId() : null)
                .repairTypeName(repairRequest.getRepairType() != null ? repairRequest.getRepairType().getName() : null)
                .build();
    }

    public static RepairRequest toEntity(RepairRequestDTO dto, Customer customer, Technician technician, RepairType repairType) {
        if (dto == null) {
            return null;
        }
        RepairRequest repairRequest = new RepairRequest();
        repairRequest.setId(dto.getId());
        repairRequest.setDescription(dto.getDescription());
        repairRequest.setStatus(dto.getStatus());
        repairRequest.setCustomer(customer);
        repairRequest.setTechnician(technician);
        repairRequest.setRepairType(repairType);
        return repairRequest;
    }
}
