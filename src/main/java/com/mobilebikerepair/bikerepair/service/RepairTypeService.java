package com.mobilebikerepair.bikerepair.service;

import com.mobilebikerepair.bikerepair.dto.RepairTypeRequestDTO;
import com.mobilebikerepair.bikerepair.dto.RepairTypeResponseDTO;

import java.util.List;


public interface RepairTypeService {
    RepairTypeResponseDTO createRepairType(RepairTypeRequestDTO dto);
    List<RepairTypeResponseDTO> getAllRepairTypes();
    RepairTypeResponseDTO getRepairTypeById(Long id);
}

