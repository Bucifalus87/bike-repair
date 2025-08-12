package com.mobilebikerepair.bikerepair.service;


import com.mobilebikerepair.bikerepair.dto.RepairTypeRequestDTO;
import com.mobilebikerepair.bikerepair.dto.RepairTypeResponseDTO;
import com.mobilebikerepair.bikerepair.model.RepairType;
import com.mobilebikerepair.bikerepair.repository.RepairTypeRepository;
import com.mobilebikerepair.bikerepair.service.RepairTypeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairTypeServiceImpl implements RepairTypeService {

    private final RepairTypeRepository repairTypeRepository;

    @Override
    public RepairTypeResponseDTO createRepairType(RepairTypeRequestDTO dto) {
        RepairType repairType = new RepairType();
        repairType.setName(dto.getName());
        repairType.setDescription(dto.getDescription());
        repairType = repairTypeRepository.save(repairType);
        return toDto(repairType);
    }

    @Override
    public List<RepairTypeResponseDTO> getAllRepairTypes() {
        return repairTypeRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public RepairTypeResponseDTO getRepairTypeById(Long id) {
        return repairTypeRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("RepairType not found"));
    }

    private RepairTypeResponseDTO toDto(RepairType repairType) {
        RepairTypeResponseDTO dto = new RepairTypeResponseDTO();
        dto.setId(repairType.getId());
        dto.setName(repairType.getName());
        dto.setDescription(repairType.getDescription());
        return dto;
    }
}

