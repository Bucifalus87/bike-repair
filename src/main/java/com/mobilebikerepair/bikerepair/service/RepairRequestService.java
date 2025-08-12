package com.mobilebikerepair.bikerepair.service;

import com.mobilebikerepair.bikerepair.dto.RepairRequestCreateDTO;
import com.mobilebikerepair.bikerepair.dto.RepairRequestUpdateDTO;
import com.mobilebikerepair.bikerepair.dto.RepairRequestResponseDTO;

import java.util.List;


public interface RepairRequestService {
    RepairRequestResponseDTO createRequest(RepairRequestCreateDTO dto);
    RepairRequestResponseDTO updateRequest(Long id, RepairRequestUpdateDTO dto);
    RepairRequestResponseDTO getById(Long id);
    List<RepairRequestResponseDTO> getAll();
    List<RepairRequestResponseDTO> getByCustomerId(Long customerId);
    List<RepairRequestResponseDTO> getByTechnicianId(Long technicianId);
}

